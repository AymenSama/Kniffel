package kniffel;

import java.util.Scanner;

public class Utils {
    static char readChar() {
        return new Scanner(System.in).next().charAt(0);
    }

    static int readInt() {
        return new Scanner(System.in).nextInt();
    }

    static void printLizenz() {
        System.out.print("\n\n\nKniffel in c++ (basierend auf Kniffel in StarBasic)\nCopyright (C) 2009 Markus Brenneis\n\nThis program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.\nYou should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.\n\n\n");
    }

    static void printMenuMessage() {
        System.out.print("\n\nKniffel in c++ " + Launcher.version + "\nCopyright (C) 2009 Markus Brenneis\n\nFür dieses Programm besteht KEINERLEI GARANTIE.\nDies ist freie Software, die Sie unter bestimmten Bedingungen weitergeben dürfen.\n\nGeben Sie L für Details ein.\n\n\n\n");
    }
}
