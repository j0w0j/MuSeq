package MuSeq.file_input;

import java.util.ArrayList;

/**
 * Contains ArrayLists for each VALID FASTA test file in src/test/test_files/
 * Each ArrayLists should match the output of FastaReader for the correspoding file.
 */
public class ExpectedFastaLines {

    private ArrayList<String> testValid1 = new ArrayList<String>();
    private ArrayList<String> testValid2 = new ArrayList<String>();
    private ArrayList<String> testValid3 = new ArrayList<String>();


    public void setTestValid1() {
        testValid1.add("tgcaccaaacatgtctaaagctggaaccaaaattactttctttgaagacaaaaactttca");
        testValid1.add("aggccgccactatgacagcgattgcgactgtgcagatttccacatgtacctgagccgctg");
        testValid1.add("caactccatcagagtggaaggaggcacctgggctgtgtatgaaaggcccaattttgctgg");
        testValid1.add("gtacatgtacatcctaccccggggcgagtatcctgagtaccagcactggatgggcctcaa");
        testValid1.add("ccggaagcccgtcgactggggtgcagc");
        testValid1.add("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        testValid1.add("T");
        testValid1.add("tgcaccaaacatgtctaaagctggaaccaaaattactttctttgaagacaaaaactttca");
        testValid1.add("cgaccgcctcagctc");
        // Source - https://stackoverflow.com/a/35395355
        testValid1.replaceAll(String::toUpperCase);

    }

    public void setTestValid2() {
        testValid2.add("tgcaccaaacatgtctaaagctggaaccaaaattactttctttgaagaaaaactttcaaggccgccactatgacagcg");
        testValid2.add("tgcgactgtgcagatttccacatgtaccgcaactccatcagagtggaag");
        testValid2.add("gaggcacctgggctgtgtatgaaaggccaa");
        testValid2.add("caattttgctggtacaa");
        testValid2.add("atgtacatcctaccccggggcgagtatcctgagtaccagcactggatgggc");
        testValid2.add("cgaccgcctcagctcctgcagggctgttcacctgt");
        testValid2.add("ctagt");
        testValid2.add("ggaggccagtaagtgtgtg");
        testValid2.add("GcGcGcGc");
        testValid2.add("GcGcGcGc");
        // Source - https://stackoverflow.com/a/35395355
        testValid2.replaceAll(String::toUpperCase);
    }

    public void setTestValid3() {
        testValid3.add("ACGTUACGU");
        testValid3.add("ACGTU");
        testValid3.add("ACGTU");
        testValid3.add("ACGTU");
        testValid3.add("ACGTU");
        testValid3.add("ACGTU");
        testValid3.add("ACGTU");
        testValid3.add("ACAGUACAGTU");
        testValid3.add("AAAAAAAAAAAAAAAAAAAAAA");
        // Source - https://stackoverflow.com/a/35395355
        testValid3.replaceAll(String::toUpperCase);
    }

    public ArrayList<String> getTestValid1() {
        return testValid1;
    }

    public ArrayList<String> getTestValid2() {
        return testValid2;
    }

    public ArrayList<String> getTestValid3() {
        return testValid3;
    }


    public ExpectedFastaLines(){
        setTestValid1();
        setTestValid2();
        setTestValid3();
    }
}
