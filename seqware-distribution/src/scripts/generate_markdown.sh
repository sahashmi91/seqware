#! /bin/bash
java -jar target/seqware-distribution-0.13.6.5-full.jar  -p net.sourceforge.seqware.pipeline.plugins.MarkdownPlugin | tail -n +3 > docs/site/content/docs/17-plugins.md
java -jar target/seqware-distribution-0.13.6.5-full.jar  -p net.sourceforge.seqware.pipeline.plugins.MarkdownPlugin -- -m | tail -n +3 > docs/site/content/docs/17a-modules.md