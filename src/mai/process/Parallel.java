package mai.process;

import mai.models.Cube;
import mai.models.Dictionary;

import java.util.ArrayList;

/**
 * extends {@link Sequential}.<br>
 * {@link #associatedDictionary()} is override from {@link Sequential#associatedDictionary()}.
 */
public class Parallel extends Sequential {

	/**
	 * maximum number of threads at the time. 
	 */
	private static final int MAX_THREADS = 20;
   
	/**
     * tell a dictionary is associated or not.
     */
    private boolean associatedDic = true;
   
    /**
     * number of threads are running. It is limited by {@link #MAX_THREADS}.
     */
    ArrayList<Thread> threadsList = new ArrayList<Thread>();

    /**
     * @param cube
     * @param dic
     */
    public Parallel(Cube cube, Dictionary dic) {
        super(cube, dic);
    }

    /**
     * visit all words in a dictionary.
     * Create a thread for each word. Then,  add the thread into {@link #threadsList}.
     * <li>if there are slots in {@link #threadsList}, add the thread.
     * <li>if there is no slot in {@link #threadsList},find threads are no long alive and kill them.
     * If found a non associated word, call all alive threads in {@link #threadsList} and stop them.<br>
     * The program stops when the size of {@link #threadsList} = 0. 
     */
    @Override
    public boolean associatedDictionary() {
        if (!this.invariant()) {
            System.out.println("The initial of program is not valid!");
            return false;
        } else {
            for (int wIndex = 0; wIndex < dictionary.getDicSize(); wIndex++) {
                if (associatedDic) {
                    final String checkingWord = dictionary.getWordList().get(wIndex);
                   
                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            if (!isFoundWord(checkingWord)) {
                                associatedDic = false;
                                for (int i = 0; i < threadsList.size(); i++) {
                                    if (threadsList.get(i).isAlive())
                                        threadsList.get(i).interrupt();
                                }
                            }

                        }
                    });
                    threadsList.add(t);
                    threadsList.get(threadsList.size() - 1).start();
                   
                    while (threadsList.size() == MAX_THREADS) {
                        for (int i = 0; i < threadsList.size(); i++) {
                            if (!threadsList.get(i).isAlive())
                                threadsList.remove(i);
                        }
                    }
                }
            }

            while (threadsList.size() > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                for (int i = 0; i < threadsList.size(); i++) {
                    if (!threadsList.get(i).isAlive())
                        threadsList.remove(i);
                }
            }
            return associatedDic;
        }
    }

}
