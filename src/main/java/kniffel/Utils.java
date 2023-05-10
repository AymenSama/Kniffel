package kniffel;

import java.util.Scanner;

public class Utils {
    public static char readChar() {
        return new Scanner(System.in).next().charAt(0);
    }

    public static int readInt() {
        return new Scanner(System.in).nextInt();
    }

    public static void printLicense() {
        System.out.print("""



                Kniffel in c++ (basierend auf Kniffel in StarBasic)
                Copyright (C) 2009 Markus Brenneis

                This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
                This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
                You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.


                """);
    }

    public static void invalidInputMessage() {
        System.out.print("Ungültige Eingabe\n\n");
    }
}
