--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- Name: sw_accession_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sw_accession_seq', 1, false);


--
-- Name: experiment_attribute_experiment_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('experiment_attribute_experiment_attribute_id_seq', 1, false);


--
-- Name: experiment_experiment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('experiment_experiment_id_seq', 1, false);


--
-- Name: experiment_library_design_experiment_library_design_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('experiment_library_design_experiment_library_design_id_seq', 1, false);


--
-- Name: experiment_link_experiment_link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('experiment_link_experiment_link_id_seq', 1, false);


--
-- Name: experiment_spot_design_experiment_spot_design_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('experiment_spot_design_experiment_spot_design_id_seq', 1, false);


--
-- Name: experiment_spot_design_read_s_experiment_spot_design_read_s_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('experiment_spot_design_read_s_experiment_spot_design_read_s_seq', 1, false);


--
-- Name: file_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('file_attribute_id_seq', 1, false);


--
-- Name: file_file_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('file_file_id_seq', 1, false);


--
-- Name: file_report_row_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('file_report_row_id_seq', 1, false);


--
-- Name: file_type_file_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('file_type_file_type_id_seq', 4, true);


--
-- Name: ius_attribute_ius_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('ius_attribute_ius_attribute_id_seq', 1, false);


--
-- Name: ius_ius_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('ius_ius_id_seq', 1, false);


--
-- Name: ius_link_ius_link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('ius_link_ius_link_id_seq', 1, false);


--
-- Name: ius_workflow_runs_ius_workflow_runs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('ius_workflow_runs_ius_workflow_runs_id_seq', 1, false);


--
-- Name: lane_attribute_lane_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('lane_attribute_lane_attribute_id_seq', 1, false);


--
-- Name: lane_lane_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('lane_lane_id_seq', 1, false);


--
-- Name: lane_link_lane_link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('lane_link_lane_link_id_seq', 1, false);


--
-- Name: lane_type_lane_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('lane_type_lane_type_id_seq', 7, true);


--
-- Name: lane_workflow_runs_lane_workflow_runs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('lane_workflow_runs_lane_workflow_runs_id_seq', 1, false);


--
-- Name: library_selection_library_selection_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('library_selection_library_selection_id_seq', 25, true);


--
-- Name: library_source_library_source_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('library_source_library_source_id_seq', 7, true);


--
-- Name: library_strategy_library_strategy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('library_strategy_library_strategy_id_seq', 21, true);


--
-- Name: organism_organism_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('organism_organism_id_seq', 56, true);


--
-- Name: platform_platform_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('platform_platform_id_seq', 28, true);


--
-- Name: processing_attribute_processing_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_attribute_processing_attribute_id_seq', 1, false);


--
-- Name: processing_experiments_processing_experiments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_experiments_processing_experiments_id_seq', 1, false);


--
-- Name: processing_files_processing_files_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_files_processing_files_id_seq', 1, false);


--
-- Name: processing_ius_processing_ius_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_ius_processing_ius_id_seq', 1, false);


--
-- Name: processing_lanes_processing_lanes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_lanes_processing_lanes_id_seq', 1, false);


--
-- Name: processing_processing_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_processing_id_seq', 1, false);


--
-- Name: processing_relationship_processing_relationship_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_relationship_processing_relationship_id_seq', 1, false);


--
-- Name: processing_samples_processing_samples_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_samples_processing_samples_id_seq', 1, false);


--
-- Name: processing_sequencer_runs_processing_sequencer_runs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_sequencer_runs_processing_sequencer_runs_id_seq', 1, false);


--
-- Name: processing_studies_processing_studies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('processing_studies_processing_studies_id_seq', 1, false);


--
-- Name: registration_registration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('registration_registration_id_seq', 4, true);


--
-- Name: sample_attribute_sample_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sample_attribute_sample_attribute_id_seq', 1, false);


--
-- Name: sample_link_sample_link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sample_link_sample_link_id_seq', 1, false);


--
-- Name: sample_relationship_sample_relationship_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sample_relationship_sample_relationship_id_seq', 1, false);


--
-- Name: sample_report_row_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sample_report_row_id_seq', 1, false);


--
-- Name: sample_sample_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sample_sample_id_seq', 1, false);


--
-- Name: sample_search_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sample_search_id_seq', 1, false);


--
-- Name: sample_search_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sample_search_attribute_id_seq', 1, false);


--
-- Name: sequencer_run_attribute_sequencer_run_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sequencer_run_attribute_sequencer_run_attribute_id_seq', 1, false);


--
-- Name: sequencer_run_sequencer_run_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('sequencer_run_sequencer_run_id_seq', 1, false);


--
-- Name: share_study_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('share_study_id_seq', 1, false);


--
-- Name: share_workflow_run_share_workflow_run_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('share_workflow_run_share_workflow_run_id_seq', 1, false);


--
-- Name: study_attribute_study_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('study_attribute_study_attribute_id_seq', 1, false);


--
-- Name: study_link_study_link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('study_link_study_link_id_seq', 1, false);


--
-- Name: study_study_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('study_study_id_seq', 1, false);


--
-- Name: study_type_study_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('study_type_study_type_id_seq', 11, true);


--
-- Name: version_version_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('version_version_id_seq', 1, false);


--
-- Name: workflow_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('workflow_attribute_id_seq', 1, false);


--
-- Name: workflow_param_value_workflow_param_value_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('workflow_param_value_workflow_param_value_id_seq', 1, false);


--
-- Name: workflow_param_workflow_param_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('workflow_param_workflow_param_id_seq', 1, false);


--
-- Name: workflow_run_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('workflow_run_attribute_id_seq', 1, false);


--
-- Name: workflow_run_param_workflow_run_param_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('workflow_run_param_workflow_run_param_id_seq', 1, false);


--
-- Name: workflow_run_workflow_run_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('workflow_run_workflow_run_id_seq', 1, false);


--
-- Name: workflow_workflow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: seqware
--

SELECT pg_catalog.setval('workflow_workflow_id_seq', 48, true);


--
-- Data for Name: library_selection; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO library_selection VALUES (2, 'PCR', 'Source material was selected by designed primers');
INSERT INTO library_selection VALUES (3, 'RANDOM PCR', 'Source material was selected by randomly generated primers');
INSERT INTO library_selection VALUES (4, 'RT-PCR', 'Source material was selected by reverse transcription PCR');
INSERT INTO library_selection VALUES (5, 'HMPR', 'Hypo-methylated partial restriction digest');
INSERT INTO library_selection VALUES (6, 'MF', 'Methyl Filtrated');
INSERT INTO library_selection VALUES (7, 'CF-S', 'Cot-filtered single/low-copy genomic DNA');
INSERT INTO library_selection VALUES (8, 'CF-M', 'Cot-filtered moderately repetitive genomic DNA');
INSERT INTO library_selection VALUES (9, 'CF-H', 'Cot-filtered highly repetitive genomic DNA');
INSERT INTO library_selection VALUES (10, 'CF-T', 'Cot-filtered theoretical single-copy genomic DNA');
INSERT INTO library_selection VALUES (11, 'MSLL', 'Methylation Spanning Linking Library');
INSERT INTO library_selection VALUES (12, 'cDNA', 'complementary DNA');
INSERT INTO library_selection VALUES (13, 'ChIP', 'Chromatin immunoprecipitation');
INSERT INTO library_selection VALUES (14, 'MNase', 'Micrococcal Nuclease (MNase) digestion');
INSERT INTO library_selection VALUES (1, 'RANDOM', 'Random selection by shearing or other method');
INSERT INTO library_selection VALUES (17, 'DNAse', 'Deoxyribonuclease (MNase) digestion');
INSERT INTO library_selection VALUES (18, 'Hybrid Selection', 'Selection by hybridization in array or solution');
INSERT INTO library_selection VALUES (19, 'Reduced Representation', 'Reproducible genomic subsets, often generated by restriction fragment size selection, containing a manageable number of loci to facilitate re-sampling');
INSERT INTO library_selection VALUES (20, 'Restriction Digest', 'DNA fractionation using restriction enzymes');
INSERT INTO library_selection VALUES (21, '5-methylcytidine antibody', 'Selection of methylated DNA fragments using an antibody raised against 5-methylcytosine or 5-methylcytidine (m5C)');
INSERT INTO library_selection VALUES (22, 'MBD2 protein methyl-CpG binding domain', 'Enrichment by methyl-CpG binding domain');
INSERT INTO library_selection VALUES (23, 'CAGE', 'Cap-analysis gene expression');
INSERT INTO library_selection VALUES (24, 'RACE', 'Rapid Amplification of cDNA Ends');
INSERT INTO library_selection VALUES (25, 'size fractionation', 'Physical selection of size appropriate targets');
INSERT INTO library_selection VALUES (15, 'other', 'Other library enrichment, screening, or selection process');
INSERT INTO library_selection VALUES (16, 'unspecified', 'Library enrichment, screening, or selection is not specified');


--
-- Data for Name: library_source; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO library_source VALUES (1, 'GENOMIC', 'Genomic DNA (includes PCR products from genomic DNA)');
INSERT INTO library_source VALUES (6, 'TRANSCRIPTOMIC', 'Transcription products or non genomic DNA (EST, cDNA, RT-PCR, screened libraries)');
INSERT INTO library_source VALUES (7, 'METAGENOMIC', 'Mixed material from metagenome');
INSERT INTO library_source VALUES (2, 'NON GENOMIC', 'Deprecated - Use TRANSCRIPTOMIC or METAGENOMIC');
INSERT INTO library_source VALUES (3, 'SYNTHETIC', 'Synthetic DNA');
INSERT INTO library_source VALUES (4, 'VIRAL RNA', 'Viral RNA');
INSERT INTO library_source VALUES (5, 'OTHER', 'Other, unspecified, or unknown library source material');


--
-- Data for Name: library_strategy; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO library_strategy VALUES (3, 'CLONE', 'Genomic clone based (hierarchical) sequencing');
INSERT INTO library_strategy VALUES (4, 'POOLCLONE', 'Shotgun of pooled clones (usually BACs and Fosmids)');
INSERT INTO library_strategy VALUES (5, 'AMPLICON', 'Sequencing of overlapping or distinct PCR or RT-PCR products');
INSERT INTO library_strategy VALUES (6, 'BARCODE', 'Sequencing of overlapping or distinct  products that have been tagged with a short identifying sequence (barcode).  Each sequence read can therefore be assigned to an individual product');
INSERT INTO library_strategy VALUES (7, 'CLONEEND', 'Clone end (5'', 3'', or both) sequencing');
INSERT INTO library_strategy VALUES (8, 'FINISHING', 'Sequencing intended to finish (close) gaps in existing coverage');
INSERT INTO library_strategy VALUES (9, 'ChIP-Seq', 'Direct sequencing of chromatin immunoprecipitates');
INSERT INTO library_strategy VALUES (10, 'MNase-Seq', 'Direct sequencing following MNase digestion');
INSERT INTO library_strategy VALUES (11, 'EST', 'Single pass sequencing of cDNA templates');
INSERT INTO library_strategy VALUES (12, 'FL-cDNA', 'Full-length sequencing of cDNA templates');
INSERT INTO library_strategy VALUES (13, 'CTS', 'Concatenated Tag Sequencing');
INSERT INTO library_strategy VALUES (14, 'OTHER', 'Library strategy not listed');
INSERT INTO library_strategy VALUES (15, 'RNA-Seq', 'Random sequencing of whole transcriptome.');
INSERT INTO library_strategy VALUES (1, 'WGS', 'Random sequencing of the whole genome');
INSERT INTO library_strategy VALUES (2, 'WCS', 'Random sequencing of a whole chromosome or other replicon isolated from a genome');
INSERT INTO library_strategy VALUES (16, 'WXS', 'Random sequencing of exonic regions selected from the genome');
INSERT INTO library_strategy VALUES (17, 'DNase-Hypersensitivity', 'Sequencing of hypersensitive sites, or segments of open chromatin that are more readily cleaved by DNaseI');
INSERT INTO library_strategy VALUES (18, 'Bisulfite-Seq', 'Sequencing following treatment of DNA with bisulfite to convert cytosine residues to uracil depending on methylation status');
INSERT INTO library_strategy VALUES (19, 'MRE-Seq', 'Methylation-Sensitive Restriction Enzyme Sequencing strategy');
INSERT INTO library_strategy VALUES (20, 'MeDIP-Seq', 'Methylated DNA Immunoprecipitation Sequencing strategy');
INSERT INTO library_strategy VALUES (21, 'MBD-Seq', 'Direct sequencing of methylated fractions sequencing strategy');


--
-- Data for Name: experiment_library_design; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: experiment_spot_design; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: platform; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO platform VALUES (1, 'LS454', 'GS 20', '454 technology use 1-color sequential flows');
INSERT INTO platform VALUES (2, 'LS454', 'GS FLX', '454 technology use 1-color sequential flows');
INSERT INTO platform VALUES (3, 'LS454', '454 GS', '454 technology use 1-color sequential flows');
INSERT INTO platform VALUES (4, 'LS454', '454 GS 20', '454 technology use 1-color sequential flows');
INSERT INTO platform VALUES (5, 'LS454', '454 GS FLX', '454 technology use 1-color sequential flows');
INSERT INTO platform VALUES (6, 'LS454', 'unspecified', '454 technology use 1-color sequential flows');
INSERT INTO platform VALUES (7, 'ILLUMINA', 'Solexa 1G Genome Analyzer', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (8, 'ILLUMINA', 'Illumina Genome Analyzer', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (9, 'ILLUMINA', 'Illumina Genome Analyzer II', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (10, 'ILLUMINA', 'unspecified', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (11, 'HELICOS', 'Helicos HeliScope', 'Helicos is similar to 454 technology - uses 1-color sequential flows');
INSERT INTO platform VALUES (12, 'HELICOS', 'unspecified', 'Helicos is similar to 454 technology - uses 1-color sequential flows');
INSERT INTO platform VALUES (13, 'ABI_SOLID', 'AB SOLiD System', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (14, 'ABI_SOLID', 'AB SOLiD System 2.0', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (15, 'ABI_SOLID', 'AB SOLiD System 3.0', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (16, 'ABI_SOLID', 'AB SOLiD System 3 Plus', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (17, 'ABI_SOLID', 'AB SOLiD System 4', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (18, 'ABI_SOLID', 'AB SOLiD System PI', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (19, 'ABI_SOLID', 'unspecified', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (20, 'ILLUMINA', 'Illumina HiSeq 2000', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (21, 'ILLUMINA', 'Illumina HiSeq 1000', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (22, 'ILLUMINA', 'Illumina HiScan SQ', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (23, 'ILLUMINA', 'Illumina Genome Analyzer IIx', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (24, 'ABI_SOLID', 'AB SOLiD 5500xl', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (25, 'ABI_SOLID', 'AB SOLiD 5500', 'ABI is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (26, 'ILLUMINA', 'Illumina MiSeq', 'Illumina is 4-channel flowgram with 1-to-1 mapping between basecalls and flows');
INSERT INTO platform VALUES (27, 'ION_TORRENT', 'Ion Torrent PGM', 'Ion Torrent Personal Genome Machine (PGM) from Life Technologies.');
INSERT INTO platform VALUES (28, 'PACBIO_SMRT', 'PacBio RS', 'PacificBiosciences platform type for the single molecule real time (SMRT) technology.');


--
-- Data for Name: registration; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO registration VALUES (1, 'admin@admin.com', 'admin', 'admin', 'LIMS', 'Admin', 'Institution', NULL, true, '2009-09-15 19:33:40.428035', '2009-09-15 19:33:40.428035', false, false);


--
-- Data for Name: study_type; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO study_type VALUES (1, 'Whole Genome Sequencing', 'Sequencing of a single organism.');
INSERT INTO study_type VALUES (2, 'Metagenomics', 'Sequencing of a community.');
INSERT INTO study_type VALUES (3, 'Transcriptome Analysis', 'Sequencing and characterization of transcription elements.');
INSERT INTO study_type VALUES (4, 'Resequencing', 'Sequencing of a sample with respect to a reference.');
INSERT INTO study_type VALUES (5, 'Epigenetics', 'Cellular differentiation study.');
INSERT INTO study_type VALUES (6, 'Synthetic Genomics', 'Sequencing of modified, synthetic, or transplanted genomes.');
INSERT INTO study_type VALUES (7, 'Forensic or Paleo-genomics', 'Sequencing of recovered genomic material.');
INSERT INTO study_type VALUES (8, 'Gene Regulation Study', 'Study of gene expression regulation.');
INSERT INTO study_type VALUES (9, 'Cancer Genomics', 'Study of cancer genomics.');
INSERT INTO study_type VALUES (10, 'Population Genomics', 'Study of populations and evolution through genomics.');
INSERT INTO study_type VALUES (11, 'Other', 'Study type not listed.');


--
-- Data for Name: study; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: experiment; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: experiment_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: experiment_link; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: experiment_spot_design_read_spec; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: file_type; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO file_type VALUES (1, 'fastq file', 'chemical/seq-na-fastq', 'fastq');
INSERT INTO file_type VALUES (2, 'plain text file', 'text/plain', 'txt');
INSERT INTO file_type VALUES (3, 'bam file', 'application/bam', 'bam');
INSERT INTO file_type VALUES (4, 'gzip fastq file', 'chemical/seq-na-fastq-gzip', 'gz');


--
-- Data for Name: file; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: file_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: file_report; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: lane_type; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO lane_type VALUES (1, 'genomic_sequencing', 'Genomic Sequencing');
INSERT INTO lane_type VALUES (2, 'cdna_sequencing', 'CDNA Sequencing');
INSERT INTO lane_type VALUES (3, 'chipseq', 'Chip Sequencing');
INSERT INTO lane_type VALUES (4, 'digital_gene_expression_dpnII', 'Digital Gene Expression: DPNII');
INSERT INTO lane_type VALUES (5, 'digital_gene_expression_nlaIII', 'Digital Gene Expression: NLAIII');
INSERT INTO lane_type VALUES (6, 'bisulfite_sequencing', 'Bi-sulfite Sequencing');
INSERT INTO lane_type VALUES (7, 'other (add to description)', 'other (add to description)');


--
-- Data for Name: organism; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO organism VALUES (1, 'Anolis_carolinensis', 'Anolis carolinensis', NULL, NULL);
INSERT INTO organism VALUES (2, 'Anopheles_gambiae', 'Anopheles gambiae', NULL, NULL);
INSERT INTO organism VALUES (3, 'Apis_mellifera', 'Apis mellifera', NULL, NULL);
INSERT INTO organism VALUES (4, 'Bos_taurus', 'Bos taurus', NULL, NULL);
INSERT INTO organism VALUES (5, 'Branchiostoma_floridae', 'Branchiostoma floridae', NULL, NULL);
INSERT INTO organism VALUES (6, 'Caenorhabditis_brenneri', 'Caenorhabditis brenneri', NULL, NULL);
INSERT INTO organism VALUES (7, 'Caenorhabditis_briggsae', 'Caenorhabditis briggsae', NULL, NULL);
INSERT INTO organism VALUES (9, 'Caenorhabditis_japonica', 'Caenorhabditis japonica', NULL, NULL);
INSERT INTO organism VALUES (10, 'Caenorhabditis_remanei', 'Caenorhabditis remanei', NULL, NULL);
INSERT INTO organism VALUES (11, 'Callithrix_jacchus', 'Callithrix jacchus', NULL, NULL);
INSERT INTO organism VALUES (12, 'Canis_familiaris', 'Canis familiaris', NULL, NULL);
INSERT INTO organism VALUES (13, 'Cavia_porcellus', 'Cavia porcellus', NULL, NULL);
INSERT INTO organism VALUES (14, 'Ciona_intestinalis', 'Ciona intestinalis', NULL, NULL);
INSERT INTO organism VALUES (15, 'Danio_rerio', 'Danio rerio', NULL, NULL);
INSERT INTO organism VALUES (16, 'Drosophila_ananassae', 'Drosophila ananassae', NULL, NULL);
INSERT INTO organism VALUES (17, 'Drosophila_erecta', 'Drosophila erecta', NULL, NULL);
INSERT INTO organism VALUES (18, 'Drosophila_grimshawi', 'Drosophila grimshawi', NULL, NULL);
INSERT INTO organism VALUES (19, 'Drosophila_melanogaster', 'Drosophila melanogaster', NULL, NULL);
INSERT INTO organism VALUES (20, 'Drosophila_mojavensis', 'Drosophila mojavensis', NULL, NULL);
INSERT INTO organism VALUES (21, 'Drosophila_persimilis', 'Drosophila persimilis', NULL, NULL);
INSERT INTO organism VALUES (22, 'Drosophila_pseudoobscura', 'Drosophila pseudoobscura', NULL, NULL);
INSERT INTO organism VALUES (23, 'Drosophila_sechellia', 'Drosophila sechellia', NULL, NULL);
INSERT INTO organism VALUES (24, 'Drosophila_simulans', 'Drosophila simulans', NULL, NULL);
INSERT INTO organism VALUES (25, 'Drosophila_virilis', 'Drosophila virilis', NULL, NULL);
INSERT INTO organism VALUES (26, 'Drosophila_yakuba', 'Drosophila yakuba', NULL, NULL);
INSERT INTO organism VALUES (27, 'Equus_caballus', 'Equus caballus', NULL, NULL);
INSERT INTO organism VALUES (28, 'Felis_catus', 'Felis catus', NULL, NULL);
INSERT INTO organism VALUES (29, 'Fugu_rubripes', 'Fugu rubripes', NULL, NULL);
INSERT INTO organism VALUES (30, 'Gallus_gallus', 'Gallus gallus', NULL, NULL);
INSERT INTO organism VALUES (32, 'Monodelphis_domestica', 'Monodelphis domestica', NULL, NULL);
INSERT INTO organism VALUES (34, 'Ornithorhynchus_anatinus', 'Ornithorhynchus anatinus', NULL, NULL);
INSERT INTO organism VALUES (35, 'Oryzias_latipes', 'Oryzias latipes', NULL, NULL);
INSERT INTO organism VALUES (36, 'Pan_troglodytes', 'Pan troglodytes', NULL, NULL);
INSERT INTO organism VALUES (37, 'Petromyzon_marinus', 'Petromyzon marinus', NULL, NULL);
INSERT INTO organism VALUES (39, 'Rhesus_macaque', 'Rhesus macaque', NULL, NULL);
INSERT INTO organism VALUES (40, 'SARS_coronavirus', 'SARS coronavirus', NULL, NULL);
INSERT INTO organism VALUES (41, 'Saccharomyces_cereviciae', 'Saccharomyces cereviciae', NULL, NULL);
INSERT INTO organism VALUES (42, 'Saccharomyces_cerevisiae', 'Saccharomyces cerevisiae', NULL, NULL);
INSERT INTO organism VALUES (43, 'Strongylocentrotus_purpuratus', 'Strongylocentrotus purpuratus', NULL, NULL);
INSERT INTO organism VALUES (44, 'Takifugu_rubripes', 'Takifugu rubripes', NULL, NULL);
INSERT INTO organism VALUES (45, 'Tetraodon_nigroviridis', 'Tetraodon nigroviridis', NULL, NULL);
INSERT INTO organism VALUES (46, 'Xenopus_tropicalis', 'Xenopus tropicalis', NULL, NULL);
INSERT INTO organism VALUES (48, 'Escherichia_coli_str._K-12_substr._DH10B', 'Escherichia coli str. K-12 substr. DH10B', NULL, 316385);
INSERT INTO organism VALUES (49, 'Enterobacteria_phage_phiX174', 'Enterobacteria phage phiX174', NULL, 10847);
INSERT INTO organism VALUES (50, 'Chlorocebus_pygerythrus', 'Chlorocebus pygerythrus', NULL, 60710);
INSERT INTO organism VALUES (51, 'Plasmodium_falciparum', 'Plasmodium falciparum', NULL, 5833);
INSERT INTO organism VALUES (52, 'Rhodobacter_sphaeroides', 'Rhodobacter sphaeroides', NULL, 1063);
INSERT INTO organism VALUES (53, 'Staphylococcus_aureus', 'Staphylococcus aureus', NULL, 1280);
INSERT INTO organism VALUES (54, 'OICR_Vaccinia_jx-594', 'OICR Vaccinia JX-594', NULL, NULL);
INSERT INTO organism VALUES (55, 'OICR_De_novo_assembly', 'OICR De novo assembly', NULL, NULL);
INSERT INTO organism VALUES (56, 'OICR_See_comments', 'OICR See Comments', NULL, NULL);
INSERT INTO organism VALUES (47, 'Other', 'Other (add to description)', NULL, NULL);
INSERT INTO organism VALUES (31, 'Homo_sapiens', 'Homo sapiens', NULL, 9606);
INSERT INTO organism VALUES (33, 'Mus_musculus', 'Mus musculus', NULL, 10090);
INSERT INTO organism VALUES (38, 'Rattus_norvegicus', 'Rattus norvegicus', NULL, 10116);
INSERT INTO organism VALUES (8, 'Caenorhabditis_elegans', 'Caenorhabditis elegans', NULL, 6239);


--
-- Data for Name: sample; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sequencer_run; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: lane; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: ius; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: ius_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: ius_link; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: workflow; Type: TABLE DATA; Schema: public; Owner: seqware
--

INSERT INTO workflow VALUES (28, 'FileImport', 'Imports files into the database, links them to IUSs or Lanes and creates intermediate Processings. Initially used to import files from the LIMS and attach them to IUSes.', NULL, '0.1.0', '0.10.0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2012-01-04 13:51:00', NULL, 4, NULL);


--
-- Data for Name: workflow_run; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: ius_workflow_runs; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: lane_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: lane_link; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: lane_workflow_runs; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_experiments; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_files; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_ius; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_lanes; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_relationship; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_samples; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_sequencer_runs; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: processing_studies; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sample_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sample_hierarchy; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sample_link; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sample_relationship; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sample_report; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sample_search; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sample_search_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: sequencer_run_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: share_experiment; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: share_file; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: share_lane; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: share_processing; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: share_sample; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: share_study; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: share_workflow_run; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: study_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: study_link; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: version; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: workflow_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: workflow_param; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: workflow_param_value; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: workflow_run_attribute; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- Data for Name: workflow_run_param; Type: TABLE DATA; Schema: public; Owner: seqware
--



--
-- PostgreSQL database dump complete
--

