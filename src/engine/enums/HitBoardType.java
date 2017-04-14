package engine.enums;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public enum HitBoardType {
    EMPTY {
        @Override
        public String toString() {
            return "|   ";
        }
    },
    HIT {
        @Override
        public String toString() {
            return "| X ";
        }
    },
    MISS {
        @Override
        public String toString() {
            return "| . ";
        }
    }
}
