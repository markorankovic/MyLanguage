package swift;

public class CommandDefinitions {

	public static String rectCommand = "rect " + SyntaxDefinitions.literal + ", " + SyntaxDefinitions.literal;
	public static String circleCommand = "circle " + SyntaxDefinitions.literal;
	public static String clearCommand = "clear";
	public static String colorCommand = "color " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer;
	public static String drawCommand = "draw " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer;
	public static String drawToCommand = "drawTo " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer;
	public static String resetCommand = "reset " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer;
	public static String triangleCommand = "triangle " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer;;
	public static String positionCommand = "position " + SyntaxDefinitions.integer + ", " + SyntaxDefinitions.integer;

}
