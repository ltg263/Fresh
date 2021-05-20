package com.power.fresh.temp;

/**
 * @author AlienChao
 * @date 2020/06/10 16:57
 */
public class House {


    private int width;
    private int max;

    public House(Build build) {
        this.width = build.width;
        this.max = build.max;
    }

    public static class Build {
        private int width;
        private int max;

        public Build setWidth(int width) {
            this.width = width;
            return this;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public Build() {
            width = 100;
            max = 250;
        }

        public House build() {
            return new House(this);

        }


    }


}
