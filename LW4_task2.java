import java.util.*;

class LW4_task2 {
	public static abstract class Sweet {
		private String name;
		private double weight;
		private double sugarContent;

		public Sweet(String name, double weight, double sugarContent) {
			this.name = name;
			this.weight = weight;
			this.sugarContent = sugarContent;
		}

		public String getName() {
			return name;
		}

		public double getWeight() {
			return weight;
		}

		public double getSugarContent() {
			return sugarContent;
		}

		@Override
		public String toString() {
			return "Sweet{" +
					"name='" + name + '\'' +
					", weight=" + weight +
					", sugarContent=" + sugarContent +
					'}';
		}

		public abstract String getType();
	}

	public static class Candy extends Sweet {
		private String flavor;

		public Candy(String name, double weight, double sugarContent, String flavor) {
			super(name, weight, sugarContent);
			this.flavor = flavor;
		}

		public String getFlavor() {
			return flavor;
		}

		@Override
		public String getType() {
			return "Candy";
		}

		@Override
		public String toString() {
			return getType() + "{" +
					"flavor='" + flavor + '\'' +
					", " + super.toString() +
					'}';
		}
	}

	public static class Chocolate extends Sweet {
		private String type;

		public Chocolate(String name, double weight, double sugarContent, String type) {
			super(name, weight, sugarContent);
			this.type = type;
		}

		public String getType() {
			return "Chocolate";
		}

		@Override
		public String toString() {
			return getType() + "{" +
					"type='" + type + '\'' +
					", " + super.toString() +
					'}';
		}
	}

	public static class Gift {
		private List<Sweet> sweets = new ArrayList<>();

		public void addSweet(Sweet sweet) {
			sweets.add(sweet);
		}

		public double calculateWeight() {
			return sweets.stream().mapToDouble(Sweet::getWeight).sum();
		}

		public void sortSweetsBySugarContent() {
			sweets.sort(Comparator.comparingDouble(Sweet::getSugarContent));
		}

		public Sweet findSweetBySugarContent(double minSugarContent, double maxSugarContent) {
			return sweets.stream()
					.filter(sweet -> sweet.getSugarContent() >= minSugarContent
							&& sweet.getSugarContent() <= maxSugarContent)
					.findFirst()
					.orElse(null);
		}

		public void displayGiftContents() {
			System.out.println("Gift Contents:");
			sweets.forEach(System.out::println);
			System.out.println("Total Weight: " + calculateWeight() + " grams\n");
		}
	}

	public static void main(String[] args) {
		Candy candy1 = new Candy("ChocoBar", 50, 30, "Chocolate");
		Candy candy2 = new Candy("Gummy Bears", 30, 25, "Fruit");
		Chocolate chocolate = new Chocolate("Dark Chocolate", 80, 40, "Bitter");

		Gift gift = new Gift();
		gift.addSweet(candy1);
		gift.addSweet(candy2);
		gift.addSweet(chocolate);

		gift.displayGiftContents();

		gift.sortSweetsBySugarContent();
		System.out.println("Gift Contents after sorting by sugar content:");
		gift.displayGiftContents();

		double minSugarContent = 20;
		double maxSugarContent = 35;
		Sweet sweetInRange = gift.findSweetBySugarContent(minSugarContent, maxSugarContent);
		if (sweetInRange != null) {
			System.out.println("Found sweet in the specified sugar content range: " + sweetInRange);
		} else {
			System.out.println("No sweet found in the specified sugar content range.");
		}
	}
}
