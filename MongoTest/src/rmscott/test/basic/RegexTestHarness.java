/**
 * 
 */
package rmscott.test.basic;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rmscott
 *
 */
public class RegexTestHarness {

	public static void main(String[] args) {
		System.out.println("Starting RegexTestHarness .....");
		System.out.println();

		boolean keepLooping = false;
		Scanner scanner = null;
		
		do {
			boolean found = false;
			scanner = new Scanner(new InputStreamReader(System.in));

			System.out.println();
			System.out.println("Enter your regex: ");
			String line1 = scanner.nextLine();
			/*
			 * BufferedReader bufferedReader = new BufferedReader(new
			 * InputStreamReader(System.in)); String line1 =
			 * bufferedReader.readLine();
			 */
			Pattern pattern = Pattern.compile(line1);

			System.out.println("Enter input string to search: ");
			String line2 = scanner.nextLine();
			if (line2 != null && line2.length() > 1) {
				keepLooping = true;
				Matcher matcher = pattern.matcher(line2);

				while (matcher.find()) {
					StringBuffer sb = new StringBuffer();
					sb.append("I found the text ");
					sb.append(matcher.group());
					sb.append(" starting at index ");
					sb.append(matcher.start());
					sb.append(" and ending at index ");
					sb.append(matcher.end());
					sb.append(" index ");
					System.out.println(sb.toString());
					found = true;
				}
				if (!found) {
					System.out.println("No match found");
				}
				System.out.println();
				System.out.println();
			}
		} while (keepLooping); // end of while
		if( scanner != null) {
			scanner.close();
		}

	} // end of main
}
