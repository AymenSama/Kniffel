/*
Kniffel in c++ (basierend auf Kniffel in StarBasic)
Copyright (C) 2009 Markus Brenneis
(2023 nach Java portiert)

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.
*/

import java.util.Scanner;

public class Kniffel {
    static int felder[] = new int[13], feldges[] = new int[13], wurfel[] = new int [5], wurfels[] = new int [5], gesoben, gesunten, ges, gesbonus, bonus, wurfe;
    static String version="v1.0a1-090714", lang="lang_en";
    static char[] wurfges = new char[5];

    public static void main(String[] args){
        if(args.length>1) {
            String par=args[1];
            if(par.equals("--help") || par.equals("/?") || par.equals("-h")){
                System.out.print("Kniffel in c++ (basierend auf Kniffel in StarBasic) " + version + "\n\n");
                System.out.print("-c -no-gui    Ohne grafische Benutzeroberfläche starten\n");
                System.out.print("-l --lizenz   Lizenz anzeigen\n");
                System.out.print("-h --help /?  Hilfe anzeigen\n");
                return;
            } else if(par.equals("-l") || par.equals("--lizenz")) {
                lizenz1(); return;
            } else {
                System.out.print("Ungültige(r) Paramter ignoriert.\nVersuchen Sie " + args[0] + " --help für mehr Informationen.\n");
            }
        }

        init();
    }

    static char read() {
        return new Scanner(System.in).next().charAt(0);
    }

    static int read2() {
        return new Scanner(System.in).nextInt();
    }

    static int init(){
        char menu;
        System.out.print("\n\nKniffel in c++ " + version + "\nCopyright (C) 2009 Markus Brenneis\n\nFür dieses Programm besteht KEINERLEI GARANTIE.\nDies ist freie Software, die Sie unter bestimmten Bedingungen weitergeben dürfen.\n\nGeben Sie L für Details ein.\n\n\n\n");
        System.out.print("L: Lizenz anzeigen  N: Neues Spiel ");
        menu = read();
        switch(menu){
            case 'l':
            case 'L': lizenz(); break;
            case 'n':
            case 'N': reset(); break;
            default: System.out.print("Ungültige Eingabe\n\n"); init();
        }
        return 0;
    }

    static int lizenz() {
        lizenz1();
        char menu;
        System.out.print("N: Neues Spiel ");
        menu = read();
        switch(menu){
            case 'n':
            case 'N': reset(); break;
            default: System.out.print("Ungültige Eingabe\n\n"); lizenz();
        }
        return 0;
    }

    static int lizenz1() {
        System.out.print("\n\n\nKniffel in c++ (basierend auf Kniffel in StarBasic)\nCopyright (C) 2009 Markus Brenneis\n\nThis program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.\nYou should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.\n\n\n");
        return 0;
    }

    static int reset() {
        for (int i=0; i<13; i++) {
            felder[i]=0;
            feldges[i]=0;
        }
        for (int i=0; i<5; i++) {
            wurfel[i]=0;
            wurfges[i]='|';
        }
        wurfe=3;
        redraws(0);
        redraws(1);
        return 0;
    }

    static int redraws(int part) { //0=maingame 1=mainfooter 2=quitfooter 3=resetfooter
        char menu;
        switch(part){
            case 0:
                gesoben=0; gesunten=0;
                for (int i=0; i<6; i++) {
                    gesoben+=felder[i];
                }
                if(gesoben>=64) {
                    bonus=35;
                } else {
                    bonus=0;
                }
                gesbonus=gesoben+bonus;
                for (int i=6; i<13; i++) {gesunten+=felder[i];}
                ges=gesunten+gesbonus;
                System.out.print("\n\n\n\n\n");
                System.out.print("----------------------------------- Kniffel ------------------------------------\n");
                System.out.print("1:  1er            | " + felder[0] + "\n" +
                "2:  2er            | " + felder[1] + "\n" +
                "3:  3er            | " + felder[2] + "\n" +
                "4:  4er            | " + felder[3] + "\n" +
                "5:  5er            | " + felder[4] + "\n" +
                "6:  6er            | " + felder[5] + "\n" +
                "    Gesamt         | " + gesoben + "\n" +
                "    Bonus          | " + bonus + "\n" +
                "    Gesamt + Bonus | " + gesbonus + "\n\n" +
                "7:  Dreierpasch    | " + felder[6] + "\n" +
                "8:  Viererpasch    | " + felder[7] + "\n" +
                "9:  Full House     | " + felder[8] + "\n" +
                "10: Kleine Straße  | " + felder[9] + "\n" +
                "11: Große Straße   | " + felder[10] + "\n" +
                "12: Kniffel        | " + felder[11] + "\n" +
                "13: Chance         | " + felder[12] + "\n" +
                "    Gesamt (unten) | " + gesunten + "\n" +
                "    Gesamt (oben)  | " + gesbonus + "\n" +
                "    Gesamt         | " + ges + "\n\n  "
                       + wurfges[0] + wurfel[0] + wurfges[0] + "  " + wurfges[1] + wurfel[1] + wurfges[1] + "  " + wurfges[2] + wurfel[2] + wurfges[2] + "  " + wurfges[3] + wurfel[3] + wurfges[3] + "  " + wurfges[4] + wurfel[4] + wurfges[4] + "  noch " + wurfe + " mal\n\n");
                break;
            case 1:
                System.out.print("W: Würfeln  B: Würfel behalten  E: Eintragen  N: Neues Spiel  Q: Beenden ");
                menu = read();
                switch(menu){
                    case 'w':
                    case 'W':
                        wurfeln(); redraws(0); redraws(1); break;
                    case 'b':
                    case 'B':
                        stopwurf(); redraws(0); redraws(1); break;
                    case 'e':
                    case 'E':
                        get(); redraws(0); redraws(1); break;
                    case 'n':
                    case 'N':
                        redraws(3); break;
                    case 'q':
                    case 'Q':
                        redraws(2); break;
                    default: System.out.print("Ungülgtige Eingabe\n"); redraws(0); redraws(1);
                }
                break;
            case 2:
                System.out.print("Wirklich beenden? Alle Punkte gehen dabei verloren. (j/n) ");
                menu = read();
                switch(menu){
                    case 'j':
                    case 'J': return 0;
                    default: redraws(0); redraws(1);
                }
                break;
            case 3:
                System.out.print("Fortfahren? Alle Punkte gehen dabei verloren. (j/n) ");
                menu = read();
                switch(menu){
                    case 'j':
                    case 'J': reset(); break;
                    default: redraws(0); redraws(1);
                }
                break;
            default:
                System.out.print("error.redraws.switch.default " + part);
                return 1;
        }
        return 0;
    }

    static int wurfeln() {
        if(wurfe==0) { return 1; }
        for(int i=0; i<5; i++) {
            if (wurfges[i]=='|') {wurfel[i]=(int)(Math.random() * 6 + 1);}
        }
        wurfe--;
        return 0;
    }

    static int stopwurf() {
        if(wurfe==3) { return 1; }
        System.out.print("Zu behaltener Würfel: ");
        int menu = read2();
        wurfges[menu-1] = wurfges[menu-1]=='|' ? '*' : '|';
        return 0;
    }

    static int get() {
        if (wurfe==3) { return 1; }
        System.out.print("Eintragung bei: ");
        int menu = read2();
        if (feldges[menu-1]!=0) { System.out.print(menu + "schon besetzt"); return 1; }
        if (menu > 13 || menu < 1) {System.out.print("Ungültige Eingabe\n"); return 1;}
        if (menu < 7) {
            for (int i=0; i<5; i++) {
                if (wurfel[i]==menu) { felder[menu-1]+=menu; }
            }
        } else sort();
        if (menu == 7 || menu == 8) {
            int n=0;
            for (int i=0; i<5; i++) {
                if (wurfels[i]==wurfels[2]) { n++; }
            }
            if (n>menu-5) {
                for (int i=0; i<5; i++) { felder[menu-1]+=wurfels[i]; }
            }
        } else if (menu == 9) {
            if(wurfels[0]==wurfels[1] && wurfels[3]==wurfels[4] && (wurfels[2]==wurfels[1] || wurfels[2]==wurfels[3])) {
                felder[menu-1]=25;
            }
        } else if (menu==10 || menu==11) {
            int m=0;
            if (menu==10) {
                for(int o=0; o<3; o++) {
                    for(int n=1+o; n<5+o; n++){
                        for (int i=0; i<5; i++) {
                            if (wurfels[i]==n) { m++; break; }
                        }
                    }
                    if (m>3) {break;} else {m=0;}
                }
            }
            if (menu==11) {
                for(int o=0; o<2; o++) {
                    for(int n=1+o; n<6+o; n++){
                        for (int i=0; i<5; i++) {
                            if (wurfels[i]==n) { m++; break; }
                        }
                    }
                    if (m>4) {break;} else {m=0;}
                }
            }
            if (m>menu-7) { felder[menu-1]=30+(menu-10)*10; }
        } else if (menu == 12) {
            int n=0;
            for(int i=1; i<5; i++) {
                if (wurfels[i]==wurfels[0]) { n++; }
            }
            if (n==5) { felder[11]=50; }
        } else if (menu == 13) {
            for (int i=0; i<5; i++) {
                felder[12]+=wurfels[i];
            }
        }

        feldges[menu-1]=1;
        wurfe=3;
        for(int i=0; i<5; i++) {wurfges[i]='|';}
        return 0;
    }

    static int sort(){
        for (int i=0; i<5; i++) { wurfels[i]=wurfel[i]; }
        for(int grenze=4;grenze>0;--grenze) {
            for(int pos=0, c1;pos<grenze;++pos) {
                if(wurfels[pos]>wurfels[pos+1]){
                    c1=wurfels[pos];
                    wurfels[pos]=wurfels[pos+1];
                    wurfels[pos+1]=c1;
                }
            }
        }
        return 0;
    }


}