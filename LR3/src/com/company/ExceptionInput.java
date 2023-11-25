package com.company;

    public class ExceptionInput extends RuntimeException {

        private int number;
        private String messenge;

        public int getNumber() {
            return number;
        }
        public String getMessenge() {
            return messenge;
        }

        public ExceptionInput(String m) {

            messenge = m;
            number = 1;
        }
    }