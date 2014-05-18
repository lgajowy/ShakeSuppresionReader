package com.shakeSuppression.app.model.movement.utils;

public class Direction {
    public enum Horizontal {
        Left(-1), Right(1);

        private final int id;
        Horizontal(int id) { this.id = id; }
        public int getValue() { return id; }
    }

    public enum Vertical {
        Up(-1), Down(1);

        private final int id;
        Vertical(int id) { this.id = id; }
        public int getValue() { return id; }
    }
}
