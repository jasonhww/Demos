package org.hww.buildType.builderModeDemo.normalBuilder;

public class Phone {
    private String mName;
    private String mModel;
    private String mRam;

    public Phone(String name, String model, String ram) {
        this.mName = name;
        this.mModel = model;
        this.mRam = ram;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mName='" + mName + '\'' +
                ", mModel='" + mModel + '\'' +
                ", mRam='" + mRam + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private String model;
        private String ram;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder Ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Phone build() {
            return new Phone(name, model, ram);
        }
    }
}
