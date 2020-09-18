import java.util.*;

public class NewInstrument implements Instrument {
    private ArrayList<LinkedList<Double>> strings; // arraylist = strings and linkedlist=buffers

    /*
     * frequency = CONCERT_A * Math.pow(2.0, (i - 24) / 12.0)
     * 
     * where i is within [0, 37) range. For every one of 37 strings, you will
     * allocate a "buffer" (LinkedList<Double>) the size of which is defined as:
     * 
     * size = 44100 / frequency
     * 
     * For example, the first 6 strings could have the following sizes: 400, 378,
     * 357, 337, 318, 300
     * 
     * The constructor will initialize all buffers of all strings with zeros.
     */

    public NewInstrument() {
        strings = new ArrayList<LinkedList<Double>>();
        for (int count = 0; count <= 37; count++) {
            double frequency = 440 * Math.pow(2.0, (count - 24) / 12.0); // copied from comments
            double size = 44100 / frequency;// add this to second for loop

            LinkedList<Double> allstrings = new LinkedList<Double>();

            for (int count1 = 0; count1 < size; count1++) {
                allstrings.add(0.0);
            }

            strings.add(allstrings);
        }
    }

    public void pluck(char key) {
        String letters = "qwertyuiopasdfghjklzxcvbnm1234567890<>";
        int index = letters.indexOf(key);

        LinkedList<Double> buff = strings.get(index); // Represents the guitar string

        for (int i = 0; i < buff.size(); i++) {
            double rand = Math.random() - 0.5;
            buff.set(i, rand);// replacing 0's w random numbers
        }
    }

    /**
     * Provides a time simulation that advances the simulation one step forward
     * according to the Karplus-Strong update algorithm that:
     * 
     * 1. Takes the average of the <b>first two</b> frequency samples for every
     * string. 2. Multiplies that average by the DECAY_FACTOR set to, e.g., 0.994.
     * 3. Adds the result to the end of the buffer. 4. Removes the sample frequency
     * at the front of the buffer.
     * 
     * Note that the DECAY_FACTOR can be changed but be careful with how much you
     * tweak that number :)
     */
    public void tick() {
        for (int i = 0; i < strings.size(); i++) {
            double sum = strings.get(i).get(0) + strings.get(i).get(1);
            double finalsum = (sum / 2) * 0.994;
            strings.get(i).addLast(finalsum);
            strings.get(i).remove(0);
        }
    }

    /**
     * Returns the sum of the values from the front of the buffer of every available
     * string.
     */
    public double superposition() {
        double sum = 0;
        for (int i = 0; i < strings.size(); i++) {
            double num = strings.get(i).get(0);
            sum += num;
        }

        return sum;
    }
}