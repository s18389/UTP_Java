package TESTING;

import java.util.concurrent.atomic.AtomicLong;

public class MySequence {

        private AtomicLong currentValue = new AtomicLong(0L);

        public long getNextValue() {
            return currentValue.getAndIncrement();
        }

}
