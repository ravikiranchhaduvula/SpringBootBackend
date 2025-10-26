package DesignPatterns.CreationalPatterns.Builder;

public class House {
    private String foundation;
    private String structure;
    private String roof;
    private boolean hasGarage;
    private boolean hasSwimmingPool;
    private boolean hasGarden;
   //Constructor overloading with same parameter count not possible


    private House(HouseBuilder builder) {
        this.hasGarden = builder.hasGarden;
        this.hasSwimmingPool = builder.hasSwimmingPool;
        this.hasGarage = builder.hasGarage;
        this.roof = builder.roof;
        this.structure = builder.structure;
        this.foundation = builder.foundation;
    }

    @Override
    public String toString() {
        return "House{" +
                "foundation='" + foundation + '\'' +
                ", structure='" + structure + '\'' +
                ", roof='" + roof + '\'' +
                ", hasGarage=" + hasGarage +
                ", hasSwimmingPool=" + hasSwimmingPool +
                ", hasGarden=" + hasGarden +
                '}';
    }

    public static class HouseBuilder {
        private String foundation;
        private String structure;
        private String roof;
        private boolean hasGarage;
        private boolean hasSwimmingPool;
        private boolean hasGarden;

        public HouseBuilder(String foundation, String structure, String roof) {
            this.foundation = foundation;
            this.structure = structure;
            this.roof = roof;
        }

        // Optional parameters
        public HouseBuilder setGarden(boolean hasGarden) {
            this.hasGarden = hasGarden;
            return this;
        }

        public HouseBuilder setSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        public HouseBuilder setGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}
