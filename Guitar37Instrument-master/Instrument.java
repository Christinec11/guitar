public interface Instrument {
    /**
     * Initializes the sample frequency buffer with random numbers in a range of
     * [-0.5; 0.5] for every string.
     * 
     * @param key Defines the key pressed by the user that you want to correspond to
     *            a specific string on your "Guitar 37" instrument. The easiest way
     *            to process it is to create an alphabet of 37 keys and use the
     *            corresponding index of the key in the alphabet as a reference to a
     *            specific string on your "Guitar 37" instrument. However, it is up
     *            to you how you want to implement it.
     */
    public void pluck(char key);

    /**
     * Provides a time simulation that advances the simulation one step forward
     * according to the Karplus-Strong update algorithm that:
     * 
     * 1. Takes the average of the <b>first two</b> frequency samples for every
     * string. 
     * 2. Multiplies that average by the DECAY_FACTOR set to, e.g., 0.994.
     * 3. Adds the result to the end of the buffer. 
     * 4. Removes the sample frequency at the front of the buffer.
     * 
     * Note that the DECAY_FACTOR can be changed but be careful with how much you
     * tweak that number :)
     */
    public void tick();

    /**
     * Returns the sum of the values from the front of the buffer of every available
     * string.
     */
    public double superposition();
}
