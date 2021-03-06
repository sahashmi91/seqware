package com.github.seqware.queryengine.model.test;

import com.github.seqware.queryengine.Benchmarking;
import com.github.seqware.queryengine.factory.CreateUpdateManager;
import com.github.seqware.queryengine.factory.SWQEFactory;
import com.github.seqware.queryengine.impl.HBasePersistentBackEnd;
import com.github.seqware.queryengine.impl.MRHBaseModelManager;
import com.github.seqware.queryengine.kernel.Compression;
import com.github.seqware.queryengine.kernel.RPNStack;
import com.github.seqware.queryengine.kernel.RPNStack.Constant;
import com.github.seqware.queryengine.kernel.RPNStack.FeatureAttribute;
import com.github.seqware.queryengine.kernel.RPNStack.Operation;
import com.github.seqware.queryengine.kernel.RPNStack.TagHierarchicalOccurrence;
import com.github.seqware.queryengine.kernel.RPNStack.TagOccurrence;
import com.github.seqware.queryengine.kernel.RPNStack.TagValuePresence;
import com.github.seqware.queryengine.model.*;
import com.github.seqware.queryengine.plugins.PluginInterface;
import com.github.seqware.queryengine.plugins.plugins.FeaturesByAttributesPlugin;
import com.github.seqware.queryengine.system.importers.OBOImporter;
import com.github.seqware.queryengine.util.SGID;
import java.io.File;
import java.util.Iterator;
import org.antlr.runtime.RecognitionException;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests of {@link com.github.seqware.queryengine.model.QueryInterface}
 *
 * @author dyuen
 * @author jbaran
 * @version $Id: $Id
 * @since 0.13.3
 */
public class QueryInterfaceTest implements Benchmarking {

    private static FeatureSet aSet, bSet, benchmarkSet, cSet;
    private static Feature a1, a2, a3, a4;
    private static TagSet b_tagset;
    private static TagSet c_tagset;

    /**
     * <p>setupTests.</p>
     */
    @BeforeClass
    public static void setupTests() {
        CreateUpdateManager mManager = SWQEFactory.getModelManager();

        aSet = mManager.buildFeatureSet().setReference(mManager.buildReference().setName("Dummy_ref").build()).build();
        // create and store some features
        a1 = mManager.buildFeature().setSeqid("chr16").setStart(1000000).setStop(1000100).setStrand(Feature.Strand.NEGATIVE).setType("type1").setScore(100.0).setSource("Program A").setPragma("pragma").setPhase(".").build();
        a2 = mManager.buildFeature().setSeqid("chr16").setStart(1000001).setStop(1000101).setStrand(Feature.Strand.POSITIVE).setType("type2").setScore(80.0).setSource("Program A").setPragma("pragma").setPhase(".").build();
        a3 = mManager.buildFeature().setSeqid("chr16").setStart(1000002).setStop(1000102).setStrand(Feature.Strand.NOT_STRANDED).setType("type2").setScore(80.0).setSource("Program B").setPragma("pragma").setPhase(".").build();
        a4 = mManager.buildFeature().setSeqid("chr16").setStart(1000003).setStop(1000103).setStrand(Feature.Strand.UNKNOWN).setType("type3").setScore(50.0).setSource("Program B").setPragma("pragma").setPhase(".").build();
        aSet.add(a1);
        aSet.add(a2);
        aSet.add(a3);
        aSet.add(a4);
        b_tagset = mManager.buildTagSet().build();
        bSet = FeatureStoreInterfaceTest.diverseBSet(mManager, b_tagset);
        c_tagset = mManager.buildTagSet().build();
        cSet = FeatureStoreInterfaceTest.diverseCSet(mManager, c_tagset);
        mManager.flush();

        if (BENCHMARK) {
            benchmarkSet = FeatureStoreInterfaceTest.largeTestSet(mManager, BENCHMARK_FEATURES);
        }

        //TODO: this test was somewhat invalid, no flush ... causes error ... we may want a new test case with a nice clean error message
        mManager.flush();
    }

    /**
     * <p>testGetFeatures.</p>
     */
    @Test
    public void testGetFeatures() {
        // get a FeatureSet from the back-end without creating a new set
        FeatureSet atomBySGID = SWQEFactory.getQueryInterface().getAtomBySGID(FeatureSet.class, aSet.getSGID());
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;
        for (Feature f : atomBySGID) {
            // sadly, Features no longer will be exactly the same after a query, queries generate new features
            // in new FeatureSets
            if (f.getStart() == a1.getStart() && f.getStop() == a1.getStop()) {
                b1 = true;
            } else if (f.getStart() == a2.getStart() && f.getStop() == a2.getStop()) {
                b2 = true;
            } else if (f.getStart() == a3.getStart() && f.getStop() == a3.getStop()) {
                b3 = true;
            }
        }
        Assert.assertTrue(atomBySGID.getCount() + " features did not match via query interface getAtomBySGID()", b1 && b2 && b3);

        // get a FeatureSet from the back-end while creating a new set
        QueryFuture<FeatureSet> future = SWQEFactory.getQueryInterface().getFeatures(0, aSet);

        // check that Features are present match
        FeatureSet result = future.get();

        b1 = false;
        b2 = false;
        b3 = false;
        for (Feature f : result) {
            // sadly, Features no longer will be exactly the same after a query, queries generate new features
            // in new FeatureSets
            if (f.getStart() == a1.getStart() && f.getStop() == a1.getStop()) {
                b1 = true;
            } else if (f.getStart() == a2.getStart() && f.getStop() == a2.getStop()) {
                b2 = true;
            } else if (f.getStart() == a3.getStart() && f.getStop() == a3.getStop()) {
                b3 = true;
            }
        }
        Assert.assertTrue(result.getCount() + " features did not match via query interface getFeatures() plug-in ", b1 && b2 && b3);

        if (BENCHMARK) {
            future = SWQEFactory.getQueryInterface().getFeatures(0, benchmarkSet);

            this.benchmark("getFeatures(...)", future);
        }
    }

    private void benchmark(String title, QueryFuture<FeatureSet> future) {
        long[] totalTimes = new long[BENCHMARK_RUNS];

        long totalRunTime = System.currentTimeMillis();
        for (int run = 0; run < BENCHMARK_RUNS; run++) {
            totalTimes[run] = System.currentTimeMillis();

            FeatureSet result = future.get();
            Iterator<Feature> iterator = result.getFeatures();
            while (iterator.hasNext()) {
                iterator.next();
            }

            totalTimes[run] = System.currentTimeMillis() - totalTimes[run];
        }
        totalRunTime = System.currentTimeMillis() - totalRunTime;

        System.out.println("Benchmarking results of " + title + ":");
        System.out.println(" features in set:\t" + BENCHMARK_FEATURES);

        long sum = 0;
        for (int run = 0; run < BENCHMARK_RUNS; run++) {
            System.out.println(" get() + iteration through set " + (run + 1) + ":\t" + totalTimes[run] + "\t(in ms)");
            sum += totalTimes[run];
        }

        System.out.println(" average:\t" + (1. * sum / totalTimes.length) + "\t(in ms)");
        System.out.println(" total runtime: " + totalRunTime);
    }

    /**
     * <p>testGetFeaturesByRange.</p>
     */
    @Test
    public void testGetFeaturesByRange() {
        // get a FeatureSet from the back-end
        String structure = "chr16";
        int start = 1000000;
        int stop = 1000105;
        QueryFuture<FeatureSet> future = SWQEFactory.getQueryInterface().getFeaturesByRange(0, bSet, QueryInterface.Location.INCLUDES, structure, start, stop);

        int featuresInRange = 0;
        for (Feature feature : bSet) {
            if (feature.getSeqid().equals(structure) && feature.getStart() >= start && feature.getStop() <= stop) {
                featuresInRange++;
            }
        }

        FeatureSet result = future.get();

        Assert.assertTrue("Number of returned features within the given range does not match the number of features that were stored there, expected " + featuresInRange + ", got " + result.getCount(), result.getCount() == featuresInRange);

        if (BENCHMARK) {
            future = SWQEFactory.getQueryInterface().getFeaturesByRange(0, benchmarkSet, QueryInterface.Location.INCLUDES, "chr1", 10000, 100000);

            this.benchmark("getFeaturesByRange(...)", future);
        }
    }

    /**
     * <p>testTypeQuery.</p>
     */
    @Test
    public void testTypeQuery() {
        // get a FeatureSet from the back-end
        QueryFuture<FeatureSet> future = SWQEFactory.getQueryInterface().getFeaturesByAttributes(0, aSet, new RPNStack(
                new Constant("type1"), new FeatureAttribute("type"), Operation.EQUAL));
        // check that Features are present match
        FeatureSet result = future.get();
        for (Feature f : result) {
            Assert.assertTrue(f.getType().equals("type1"));
        }
        int count = (int) result.getCount();
        Assert.assertTrue("Query results wrong, expected 1 and found " + count, count == 1);
    }

    /**
     * <p>testInstallAndRunArbitraryPlugin.</p>
     */
    @Test
    public void testInstallAndRunArbitraryPlugin() {
        Class<? extends PluginInterface> arbitraryPlugin;
        // only use the M/R plugin for this test if using MR
        if (SWQEFactory.getModelManager() instanceof MRHBaseModelManager) {
            // pretend that the included com.github.seqware.queryengine.plugins.hbasemr.MRFeaturesByAttributesPlugin is an external plug-in
            arbitraryPlugin = FeaturesByAttributesPlugin.class;
        } else {
            // pretend the equivalent for a non-HBase back-end
            arbitraryPlugin = FeaturesByAttributesPlugin.class;
        }
        // get a FeatureSet from the back-end
        QueryFuture<FeatureSet> future = SWQEFactory.getQueryInterface().getFeaturesByPlugin(0, arbitraryPlugin, aSet, new RPNStack(
                new Constant("type1"), new FeatureAttribute("type"), Operation.EQUAL));
        // check that Features are present match
        FeatureSet result = future.get();
        for (Feature f : result) {
            Assert.assertTrue(f.getType().equals("type1"));
        }
        int count = (int) result.getCount();
        Assert.assertTrue("Query results wrong, expected 1 and found " + count, count == 1);
    }

    /**
     * <p>complexQueryTest.</p>
     */
    @Test
    public void complexQueryTest() {
        // This version of complexQueryTest is model-agnostic and will run on all back-ends.
//
//        CreateUpdateManager mManager = SWQEFactory.getModelManager();
//        try {
//            mManager.persist(bSet);
//        } catch (Exception e) {
//            Logger.getLogger(SimplePersistentBackEndTest.class.getName()).fatal("Exception", e);
//            Assert.assertTrue("Backend could not store the given FeatureSet.", false);
//        }

        QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, new RPNStack(
                new Constant("chr16"),
                new FeatureAttribute("seqid"),
                Operation.EQUAL));
        FeatureSet resultSet = queryFuture.get();
        int count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with 1 operation on 'id' failed, expected 10 and found " + count, count == 10);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, new RPNStack(
                new Constant(Feature.Strand.NEGATIVE),
                new FeatureAttribute("strand"),
                Operation.EQUAL));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with 1 operation on 'strand' failed, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, new RPNStack(
                new Constant(Feature.Strand.NEGATIVE),
                new FeatureAttribute("strand"),
                Operation.EQUAL,
                new Constant("chr16"),
                new FeatureAttribute("seqid"),
                Operation.EQUAL,
                Operation.AND));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with 3 operations failed, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, new RPNStack(
                new Constant(Feature.Strand.POSITIVE),
                new FeatureAttribute("strand"),
                Operation.EQUAL,
                new TagOccurrence(b_tagset.getSGID().getRowKey(), "SO_term"),
                Operation.AND));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints over one feature attribute and testing for presence of a specific tag failed, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, new RPNStack(
                new Constant(Feature.Strand.POSITIVE),
                new FeatureAttribute("strand"),
                Operation.EQUAL,
                new TagValuePresence(b_tagset.getSGID().getRowKey(), "SO_id", Tag.ValueType.STRING, Compression.getSequenceOntologyAccessionSurrogate("SO:0000149")),
                Operation.AND));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints over one feature attribute and testing the value of a specific tag failed, expected 2 and found " + count, count == 2);

        if (!(SWQEFactory.getQueryInterface() instanceof HBasePersistentBackEnd)) {
            // in-memory models are too slow for the following test
            return;
        }

        // Load the Sequence Ontology first:
        String curDir = System.getProperty("user.dir");
        File file = new File(curDir + "/src/test/resources/com/github/seqware/queryengine/system/so.obo");
        SGID sequenceOntologyTagSetID = OBOImporter.mainMethod(new String[]{file.getAbsolutePath()});

        TagSet tagset = SWQEFactory.getQueryInterface().getAtomBySGID(TagSet.class, sequenceOntologyTagSetID);
        CreateUpdateManager modelManager = SWQEFactory.getModelManager();
        modelManager.persist(tagset);

        /**
         * * HARDCODING OF THE OBO HIERARCHY FOR ONE TEST-CASE FOR
         * QUERYINTERFACETEST **
         */
        String key2 = Compression.getSequenceOntologyAccessionSurrogate("SO:0000110")
                + " "
                + Compression.getSequenceOntologyAccessionSurrogate("SO:0000001").replaceFirst("^SO:", "")
                + " "
                + Compression.getSequenceOntologyAccessionSurrogate("SO:0001410").replaceFirst("^SO:", "")
                + " "
                + Compression.getSequenceOntologyAccessionSurrogate("SO:0001248").replaceFirst("^SO:", "")
                + " "
                + Compression.getSequenceOntologyAccessionSurrogate("SO:0000353").replaceFirst("^SO:", "")
                + " "
                + Compression.getSequenceOntologyAccessionSurrogate("SO:0000149").replaceFirst("^SO:", "");

        Tag build = modelManager.buildTag().setKey(key2).build();
        tagset.add(build);
        modelManager.close();

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, new RPNStack(
                new Constant("chr16"),
                new FeatureAttribute("seqid"),
                Operation.EQUAL,
                new TagHierarchicalOccurrence(sequenceOntologyTagSetID.getRowKey(), Compression.getSequenceOntologyAccessionSurrogate("SO:0001410")),
                Operation.AND));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints over one feature attribute and for a (possibly parent) term in a tree hierarchy failed, expected 2 and found " + count, count == 2);
    }

    /**
     * <p>queryLanguageTest.</p>
     *
     * @throws org.antlr.runtime.RecognitionException if any.
     */
    @Test
    public void queryLanguageTest() throws RecognitionException {
//        CreateUpdateManager mManager = SWQEFactory.getModelManager();
//        try {
//            mManager.persist(bSet);
//        } catch (Exception e) {
//            Logger.getLogger(SimplePersistentBackEndTest.class.getName()).fatal("Exception", e);
//            Assert.assertTrue("Backend could not store the given FeatureSet.", false);
//        }

        QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery("seqid==\"chr16\""));
        FeatureSet resultSet = queryFuture.get();
        int count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with 1 operation on 'id' failed, expected 10 and found " + count, count == 10);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery("strand==NEGATIVE_STRAND"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with 1 operation on 'strand' failed, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery("seqid==\"chr16\"&&strand==NEGATIVE_STRAND"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with 3 operations failed, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery("seqid!=\"chr16\""));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraint with a != operation failed, expected 7 and found " + count, count == 7);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery("!(tagOccurrence(\"" + b_tagset.getSGID().getRowKey() + "\",\"SO::sequence_variant::functional_variant::transcript_function_variant::transcript_processing_variant\"))"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraint with a ! operation failed, expected 15 and found " + count, count == 15);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery(
                "!(" + "tagOccurrence(\"" + b_tagset.getSGID().getRowKey() + "\",\"" + FeatureStoreInterfaceTest.SOSEQUENCE_VARIANTFUNCTIONAL_VARIANT + "\")"
                + " || tagOccurrence(\"" + b_tagset.getSGID().getRowKey() + "\",\"" + FeatureStoreInterfaceTest.SOSEQUENCE_VARIANTFUNCTIONAL_VARIANT2 + "\")"
                + " || tagOccurrence(\"" + b_tagset.getSGID().getRowKey() + "\",\"" + FeatureStoreInterfaceTest.SOSEQUENCE_VARIANTSTRUCTURAL_VARIANT + "\")"
                + ")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraint with a complex ! operation failed, expected 13 and found " + count, count == 13);
    }

    /**
     * <p>queryLanguageTagValuePresenceTest.</p>
     *
     * @throws org.antlr.runtime.RecognitionException if any.
     */
    @Test
    public void queryLanguageTagValuePresenceTest() throws RecognitionException {
        QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery("tagValuePresence(\"" + b_tagset.getSGID().getRowKey() + "\",\"SO_term\",\"region\")"));
        FeatureSet resultSet = queryFuture.get();
        int count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 1 and found " + count, count == 1);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, bSet, RPNStack.compileQuery("tagValuePresence(\"" + b_tagset.getSGID().getRowKey() + "\",\"SO_term\",\"region42\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 0 and found " + count, count == 0);
    }

    /**
     * <p>queryLanguageTagValue_GT_R_Test.</p>
     *
     * @throws org.antlr.runtime.RecognitionException if any.
     */
    @Test
    public void queryLanguageTagValue_GT_R_Test() throws RecognitionException {
        // test > on right
        QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") > 0"));
        FeatureSet resultSet = queryFuture.get();
        int count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") > 1"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") > 2"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 1 and found " + count, count == 1);

        // test >= on right
        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") >= 0"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 4 and found " + count, count == 4);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") >= 1"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") >= 2"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);
    }

    /**
     * <p>queryLanguageTagValue_GT_L_Test.</p>
     *
     * @throws org.antlr.runtime.RecognitionException if any.
     */
    @Test
    public void queryLanguageTagValue_GT_L_Test() throws RecognitionException {
        // test > on left
        QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("0 > tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        FeatureSet resultSet = queryFuture.get();
        int count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 1 and found " + count, count == 1);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("1 > tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("2 > tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        // test >= on left
        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("0 >= tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("1 >= tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("2 >= tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 4 and found " + count, count == 4);
    }

    /**
     * <p>queryLanguageTagValue_LS_R_Test.</p>
     *
     * @throws org.antlr.runtime.RecognitionException if any.
     */
    @Test
    public void queryLanguageTagValue_LS_R_Test() throws RecognitionException {
        // test < on right
        QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") < 0"));
        FeatureSet resultSet = queryFuture.get();
        int count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 1 and found " + count, count == 1);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") < 1"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") < 2"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        // test <= on right
        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") <= 0"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") <= 1"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\") <= 2"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 4 and found " + count, count == 4);
    }

    /**
     * <p>queryLanguageTagValue_LS_L_Test.</p>
     *
     * @throws org.antlr.runtime.RecognitionException if any.
     */
    @Test
    public void queryLanguageTagValue_LS_L_Test() throws RecognitionException {
        // test < on left
        QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("0 < tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        FeatureSet resultSet = queryFuture.get();
        int count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("1 < tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("2 < tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 1 and found " + count, count == 1);

        // test <= on left
        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("0 <= tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 4 and found " + count, count == 4);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("1 <= tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 3 and found " + count, count == 3);

        queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("2 <= tagValue(\"" + c_tagset.getSGID().getRowKey() + "\",\"SO_id\")"));
        resultSet = queryFuture.get();
        count = (int) resultSet.getCount();
        Assert.assertTrue("Setting a query constraints with tagValuePresence, expected 2 and found " + count, count == 2);
    }

    /**
     * <p>testMalformedQueries.</p>
     */
    @Test
    public void testMalformedQueries() {
        try {
            QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("0 <="));
            FeatureSet resultSet = queryFuture.get();
            fail("should've thrown an exception since the query is screwed up");
        } catch (RecognitionException e) {
            // we expect an exception to be thrown when the query is faulty
        }

        try {
            QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("2 <= tagValue(,)"));
            FeatureSet resultSet = queryFuture.get();
            fail("should've thrown an exception since the query is screwed up");
        } catch (RecognitionException e) {
            // we expect an exception to be thrown when the query is faulty
        }

        try {
            QueryFuture<FeatureSet> queryFuture = SWQEFactory.getQueryInterface().getFeaturesByAttributes(1, cSet, RPNStack.compileQuery("2 <= tagWhacked(\"SO\",\"SO\")"));
            FeatureSet resultSet = queryFuture.get();
            fail("should've thrown an exception since the query is screwed up");
        } catch (RecognitionException e) {
            // we expect an exception to be thrown when the query is faulty    
        }
    }
}
