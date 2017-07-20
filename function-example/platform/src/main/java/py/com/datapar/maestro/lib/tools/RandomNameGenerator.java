package py.com.datapar.maestro.lib.tools;


import py.com.datapar.maestro.lib.utils.Lazy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Random name generator.
 * Gera nomes randomicamente, com base no adverbos e
 */
public final class RandomNameGenerator {
    private final Dictionary dictionary;
    private int pos;

    private static Lazy<RandomNameGenerator> instance = new Lazy<>(() -> new RandomNameGenerator());

    private RandomNameGenerator() {
        this.pos = (int) System.currentTimeMillis();
        this.dictionary = new Dictionary();
    }

    private synchronized String internal_next() {
        Dictionary d = dictionary;
        pos = Math.abs(pos+d.getPrime()) % d.size();
        return d.word(pos);
    }

    /**
     * Next random name string.
     *
     * @return the string
     */
    public static String next(){
        return instance.getIt().internal_next();
    }

    private class Dictionary {
        private List<String> nouns = new ArrayList<>();
        private List<String> adjectives = new ArrayList<>();

        private final int prime;

        /**
         * Instantiates a new Dictionary.
         */
        public Dictionary() {
            try {
                load("/a.txt", adjectives);
                load("/n.txt", nouns);
            } catch (IOException e) {
                throw new Error(e);
            }

            int combo = size();

            int primeCombo = 2;
            while (primeCombo<=combo) {
                int nextPrime = primeCombo+1;
                primeCombo *= nextPrime;
            }
            prime = primeCombo+1;
        }

        /**
         * Total size of the combined words.
         *
         * @return the int
         */
        public int size() {
            return nouns.size()*adjectives.size();
        }

        /**
         * Sufficiently big prime that's bigger than {@link #size()}
         *
         * @return the prime
         */
        public int getPrime() {
            return prime;
        }

        /**
         * Word string.
         *
         * @param i the
         * @return the string
         */
        public String word(int i) {
            int a = i%adjectives.size();
            int n = i/adjectives.size();

            //wa gambi...
            return adjectives.get(a)+"_"+nouns.get(n);
        }

        private void load(String name, List<String> col) throws IOException {
            BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(name),"UTF-8"));
            try {
                String line;
                while ((line=r.readLine())!=null)
                    col.add(line);
            } finally {
                r.close();
            }
        }
    }

}
