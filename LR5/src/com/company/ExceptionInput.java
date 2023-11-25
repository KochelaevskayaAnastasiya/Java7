package com.company;

    public class ExceptionInput extends RuntimeException {

        private int number;

        public int getNumber() {
            return number;
        }

        public ExceptionInput(String m) {
            super(m);
            number = 1;
        }
    }