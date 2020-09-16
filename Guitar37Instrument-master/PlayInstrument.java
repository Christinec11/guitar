/******************************************************************************
 * Name:
 * 
 * Description: Plays guitar strings when the user presses certain keys on the
 * keyboard. Karplus-Strong algorithm is used to synthesize the sound of a
 * plucked string.
 * 
 * In the constructor of the class implementing Instrument, you will initialize
 * Concert A frequency as 440. Based on that, you will create "Guitar 37"
 * strings, every frequency of which will be defined as:
 * 
 * frequency = CONCERT_A * Math.pow(2.0, (i - 24) / 12.0)
 * 
 * where i is within [0, 37) range. For every one of 37 strings, you will
 * allocate a "buffer" (LinkedList<Double>) the size of which is defined as:
 * 
 * size = 44100 / frequency
 * 
 * For example, the first 6 strings could have the following sizes:
 * 400, 378, 357, 337, 318, 300
 * 
 * The constructor will initialize all buffers of all strings with zeros.
 *
 ******************************************************************************/
public class PlayInstrument {
    public static void main(String[] args) {

        Instrument guitar = new AsianMalaysian();

        while (true) {

            // check if the user has pressed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                guitar.pluck(key);
            }

            // compute the superposition of the samples
            double sample = guitar.superposition();

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            guitar.tick();
        }
    }
}
