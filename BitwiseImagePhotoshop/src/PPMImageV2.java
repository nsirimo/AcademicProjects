import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class PPMImageV2 extends PPMImage {
	Image image;
	WritableImage writableImage;
	PixelWriter pixelWriter;
	File selectedFile;
	char[] tempChar;

	/**
	 * Constructor for the PPMImagev2 class with super() from the PPImage class. Takes in a file, and image, then pulls the pixel data from the image into a char[]. 
	 * @param fileIn
	 * @param image
	 * @throws IOException
	 */
	public PPMImageV2(File fileIn, Image image) throws IOException {
		super(fileIn);
		selectedFile = fileIn;
		this.image = image;
		writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		pixelWriter = ((WritableImage) image).getPixelWriter();
		tempChar = getPixelData();
	}

	/**
	 * Hides the message taken from the user input. 
	 * @param message
	 */
	public void hideMessage(String message) {
		int charCounter = 0;

		// Secret Message, making it into a binary string in an array string.
		String secretMessage = message + "\0";
		byte[] temp = secretMessage.getBytes();
		String[] binaryString = new String[secretMessage.toCharArray().length];
		String tempTotalString = "";

		// Turns the byte array to a string of binary numbers
		System.out.println("\n\n");
		for (int i = 0; i < temp.length; i++) {
			binaryString[i] = Integer.toBinaryString(0x100 + temp[i]).substring(1);
//			System.out.println(binaryString[i]);
		}

		// Creates the entire binary string
		for (String nString : binaryString) {
			tempTotalString += nString;
		}

		char[] binaryChar = tempTotalString.toCharArray();


		for (int i = 0; i < binaryChar.length; i++) {
			String b = Integer.toBinaryString(tempChar[i]);

			if (b.length() < 8) {
				b = "000000000".substring(0, 8 - b.length()).concat(b);
			} else {
				b = b.substring(b.length() - 8);
			}

			b = b.substring(0, b.length() - 1) + binaryChar[i];
			tempChar[i] = (char) Integer.parseInt(b, 2);
		}

	}

	/**
	 * Recovers the first message in the image that ends with \0
	 * @return string of the decoded message
	 */
	public String recoverMessage() {
		String secretMessage = "";
		// Recover message
		int zeroCounter = 0;
		int binCharCounter = 0;
		ArrayList<String> recoveredMessage = new ArrayList<String>();
		while (zeroCounter != 8) {
			String tempString = "";
			for (int i = 0; i < 8; i++) {
				String b = Integer.toBinaryString(tempChar[binCharCounter]);
				if (b.length() < 8) {
					b = "000000000".substring(0, 8 - b.length()).concat(b);
				} else {
					b = b.substring(b.length() - 8);
				}

				b = b.substring(b.length() - 1, b.length());

				if (b.equals("0")) {
					zeroCounter++;
				} else {
					zeroCounter = 0;
				}

				tempString += b;
				binCharCounter++;
			}

			recoveredMessage.add(tempString);
		}

		// Print recovered message
		for (int i = 0; i < recoveredMessage.size(); i++) {
			int charCode = Integer.parseInt(recoveredMessage.get(i), 2);
			String tempyo = new Character((char) charCode).toString();

			secretMessage += tempyo;
		}

		return secretMessage;
	}

	/**
	 * Alters the pixels to become grey.
	 */
	public void greyscale() {
		int countloop = 0;

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				double red = ((image.getPixelReader().getColor(x, y).getRed() * .299)
						+ (image.getPixelReader().getColor(x, y).getGreen() * .587)
						+ (image.getPixelReader().getColor(x, y).getBlue() * .114));
				double green = ((image.getPixelReader().getColor(x, y).getRed() * .299)
						+ (image.getPixelReader().getColor(x, y).getGreen() * .587)
						+ (image.getPixelReader().getColor(x, y).getBlue() * .114));
				double blue = ((image.getPixelReader().getColor(x, y).getRed() * .299)
						+ (image.getPixelReader().getColor(x, y).getGreen() * .587)
						+ (image.getPixelReader().getColor(x, y).getBlue() * .114));

				pixelWriter.setColor(x, y, new Color(red, green, blue, 1));
			}
		}

	}

	/**
	 * Edits the pixels in the image to become sepia tone.
	 */
	public void sepia() {
		int countloop = 0;

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				double red = ((image.getPixelReader().getColor(x, y).getRed() * .393)
						+ (image.getPixelReader().getColor(x, y).getGreen() * .769)
						+ (image.getPixelReader().getColor(x, y).getBlue() * .189));
				double green = ((image.getPixelReader().getColor(x, y).getRed() * .349)
						+ (image.getPixelReader().getColor(x, y).getGreen() * .686)
						+ (image.getPixelReader().getColor(x, y).getBlue() * .168));
				double blue = ((image.getPixelReader().getColor(x, y).getRed() * .272)
						+ (image.getPixelReader().getColor(x, y).getGreen() * .534)
						+ (image.getPixelReader().getColor(x, y).getBlue() * .131));

				if (red > 1) {
					red = 1;
				}

				if (green > 1) {
					green = 1;
				}

				if (blue > 1) {
					blue = 1;
				}

				pixelWriter.setColor(x, y, new Color(red, green, blue, 1));
			}
		}

	}

	/**
	 * Edits the image's pixels to become negative. 
	 */
	public void negative() {
		int countloop = 0;

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				double red = (1 - (image.getPixelReader().getColor(x, y).getRed()));
				double green = (1 - (image.getPixelReader().getColor(x, y).getGreen()));
				double blue = (1 - (image.getPixelReader().getColor(x, y).getBlue()));

				if (red > 1) {
					red = 1;
				}

				if (green > 1) {
					green = 1;
				}

				if (blue > 1) {
					blue = 1;
				}

				pixelWriter.setColor(x, y, new Color(red, green, blue, 1));
			}
		}
	}
}
