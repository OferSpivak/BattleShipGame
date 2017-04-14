package engine.enums;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public enum TileState {
    EMPTY {
        @Override
        public String toString() {
            return "|   ";
        }
    },
    FULL {
        @Override
        public String toString() {
            return "| O ";
        }
    },
    EMPTY_BOMBED {
        @Override
        public String toString() {
            return "| . ";
        }
    },
    SHIP_BOMBED {
        @Override
        public String toString() {
            return "| X ";
        }
    }
}
