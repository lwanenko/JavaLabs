package lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Stack;

public class CppParser {
    private int[] scanerUprTable;
    private int[][] parserUprTable;
    private MyLang lang;
    private byte[] lexema;
    private int lexemaCode;
    private int lexemaLine;
    private int lexemaPos;
    private int lexemaLen;
    private int lexemaNumb;
    private int errors;
    private int ungetChar;
    private String fileData;
    private int ungetLexemaCode;
    private static final int MAX_IDN = 32;
    private BufferedReader fs;

    public CppParser(MyLang lang0, String fname) throws IOException {
        this.lang = lang0;
        this.lexema = new byte[180];
        this.lexemaLen = 0;
        this.lexemaLine = 0;
        this.lexemaNumb = 0;
        this.errors = 0;
        this.ungetChar = 0;
        this.ungetLexemaCode = 0;
        this.lexemaPos = 0;
        this.parserUprTable = this.lang.getUprTable();
        this.scanerUprTable = new int[256];
        for (int ii = 0; ii < this.scanerUprTable.length; ++ii) {
            this.scanerUprTable[ii] = 0;
        }
        this.setClassLitera("()[];,:{}?~", 1);
        this.setClassLitera("abcdefghijklmnopqrstuvwxyz_", 2);
        this.setClassLitera("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 2);
        this.setClassLitera("0123456789", 3);
        try {
            this.fs = new BufferedReader(new FileReader(fname));
        }
        catch (Exception ee) {
            System.out.println("\u0424\u0430\u0439\u043b \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0438 " + fname + " \u043d\u0435 \u0432\u0456\u0434\u043a\u0440\u0438\u0442\u043e");
            throw new IOException();
        }
    }

    public void parserStack() {
        Stack<Integer> ss = new Stack<Integer>();
        ss.push(this.lang.getAxioma());
        this.lexemaCode = this.CppScaner();
        while (!ss.empty()) {
            int termCol;
            int ssItem = (Integer)ss.peek();
            if (ssItem > 0) {
                if (ssItem == this.lexemaCode) {
                    ss.pop();
                    this.lexemaCode = this.CppScaner();
                    continue;
                }
                if (this.lang.getLexemaText(ssItem).equals("else")) {
                    ss.pop();
                    ss.pop();
                    continue;
                }
                System.out.println("\n\u041f\u0440\u043e\u043f\u0443\u0449\u0435\u043d\u0430 \u043b\u0435\u043a\u0441\u0435\u043c\u0430 :" + this.lang.getLexemaText(ssItem));
                System.out.println("\n\u041f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u043c\u0430\u0454 \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u0456 \u043f\u043e\u043c\u0438\u043b\u043a\u0438");
                try {
                    this.fs.close();
                    return;
                }
                catch (Exception ee) {
                    return;
                }
            }
            int nontermcol = this.lang.indexNonterminal(ssItem);
            if (this.parserUprTable[nontermcol][termCol = this.lexemaCode == -1 ? this.lang.getTerminals().length : this.lang.indexTerminal(this.lexemaCode)] == 0) {
                System.out.println("\u0421\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u0430 \u043f\u043e\u043c\u0438\u043b\u043a\u0430: \u043d\u0430 \u0432\u0435\u0440\u0448\u0438\u043d\u0456 \u0441\u0442\u0435\u043a\u0430: " + this.lang.getLexemaText(ssItem) + " \u043f\u043e\u0442\u043e\u0447\u043d\u0430 \u043b\u0435\u043a\u0441\u0435\u043c\u0430 " + this.lang.getLexemaText(this.lexemaCode) + " " + this.getLexemaText());
                System.out.println("\n\u041f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u043c\u0430\u0454 \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u0456 \u043f\u043e\u043c\u0438\u043b\u043a\u0438");
                try {
                    this.fs.close();
                    return;
                }
                catch (Exception ee) {
                    return;
                }
            }
            int numrole = 0;
            if (this.parserUprTable[nontermcol][termCol] < 0) {
                int secLexema = this.CppScaner();
                this.setUngetLexema(secLexema);
                switch (this.lang.getNonTerminals()[nontermcol]) {
                    case -2147483320: {
                        if (this.lang.getLexemaText(secLexema).equals("}")) {
                            numrole = 152;
                            break;
                        }
                        numrole = 151;
                        break;
                    }
                    case -2147483218: {
                        if (secLexema == 268435481 || secLexema == 268435486 || secLexema == 268435487 || secLexema == 268435506 || secLexema == 268435488 || secLexema == 268435490 || secLexema == 268435479 || secLexema == 268435508 || secLexema == 268435482 || secLexema == 268435484 || secLexema == 268435492 || secLexema == 268435510) {
                            numrole = 202;
                            break;
                        }
                        numrole = 203;
                        break;
                    }
                    case -2147483197: {
                        if (secLexema == 268435481 || secLexema == 268435486 || secLexema == 268435487 || secLexema == 268435506 || secLexema == 268435488 || secLexema == 268435490 || secLexema == 268435479 || secLexema == 268435508 || secLexema == 268435482 || secLexema == 268435484 || secLexema == 268435492 || secLexema == 268435510) {
                            numrole = 204;
                            break;
                        }
                        numrole = 205;
                        break;
                    }
                    case -2147483144: {
                        if (secLexema == 268435716 || secLexema == 268435706) {
                            numrole = 224;
                            break;
                        }
                        numrole = 225;
                        break;
                    }
                    case -2147483123: {
                        if (secLexema == 268435615) {
                            numrole = 234;
                            break;
                        }
                        numrole = 235;
                        break;
                    }
                    default: {
                        System.out.println("\n\u041f\u043e\u043c\u0438\u043b\u043a\u0430: \u0432\u0456\u0434\u0441\u0443\u0442\u043d\u044f \u043e\u0431\u0440\u043e\u0431\u043a\u0430:" + this.lang.getNonTerminals()[nontermcol] + this.lang.getLexemaText(this.lang.getNonTerminals()[nontermcol]));
                        return;
                    }
                }
            }
            if (this.parserUprTable[nontermcol][termCol] > 0) {
                numrole = this.parserUprTable[nontermcol][termCol];
            }
            int iitmp = 0;
            Node tmp = null;
            for (Node tmp1 : this.lang.getLanguarge()) {
                if (numrole != ++iitmp) continue;
                tmp = tmp1;
                break;
            }
            int[] pravilo = tmp.getRoole();
            ss.pop();
            for (int ii = pravilo.length - 1; ii > 0; --ii) {
                ss.push(pravilo[ii]);
            }
        }
        if (this.lexemaCode != -1) {
            System.out.println("\u041b\u043e\u0433\u0456\u0447\u043d\u0438\u0439 \u043a\u0456\u043d\u0435\u0446\u044c \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0438 \u0437\u043d\u0430\u0439\u0434\u0435\u043d\u043e \u0440\u0430\u043d\u0456\u0448\u0435 \u043d\u0456\u0436 \u043a\u0456\u043d\u0435\u0446\u044c \u0444\u0430\u0439\u043b\u0430 ");
        } else {
            System.out.println("\u041f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u043e \u0432\u0456\u0440\u043d\u0430");
        }
        try {
            this.fs.close();
            this.fs = null;
            return;
        }
        catch (Exception ee) {
            return;
        }
    }

    public void parserRecursive() {
        int[][] uprTable = this.lang.getUprTable();
        int[] term = this.lang.getTerminals();
        int[] nonterm = this.lang.getNonTerminals();
        this.lexemaCode = this.CppScaner();
        if (this.localrecursive(this.lang.getAxioma())) {
            System.out.println("\n\u041f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u043e \u0432\u0456\u0440\u043d\u0430");
        } else {
            while (this.lexemaCode != -1) {
                this.lexemaCode = this.CppScaner();
            }
            System.out.println("\n\u041f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u043c\u0430\u0454 \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u0456 \u043f\u043e\u043c\u0438\u043b\u043a\u0438");
        }
        try {
            this.fs.close();
            return;
        }
        catch (Exception ee) {
            return;
        }
    }

    private boolean localrecursive(int nonterm) {
        int nontermcol = this.lang.indexNonterminal(nonterm);
        int termCol = this.lexemaCode == -1 ? this.lang.getTerminals().length : this.lang.indexTerminal(this.lexemaCode);
        if (this.parserUprTable[nontermcol][termCol] == 0) {
            System.out.println("\u0421\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u0430 \u043f\u043e\u043c\u0438\u043b\u043a\u0430: \u0441\u0442\u0430\u0440\u0442 \u0434\u043b\u044f  " + this.lang.getLexemaText(nonterm) + " \u043f\u043e\u0442\u043e\u0447\u043d\u0430 \u043b\u0435\u043a\u0441\u0435\u043c\u0430 " + this.lang.getLexemaText(this.lexemaCode) + " " + this.getLexemaText());
            System.out.println("\n\u041f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u043c\u0430\u0454 \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u0456 \u043f\u043e\u043c\u0438\u043b\u043a\u0438");
            try {
                this.fs.close();
                return false;
            }
            catch (Exception ee) {
                return false;
            }
        }
        int numrole = 0;
        if (this.parserUprTable[nontermcol][termCol] < 0) {
            int secLexema = this.CppScaner();
            this.setUngetLexema(secLexema);
            switch (this.lang.getNonTerminals()[nontermcol]) {
                case -2147483320: {
                    if (this.lang.getLexemaText(secLexema).equals("}")) {
                        numrole = 152;
                        break;
                    }
                    numrole = 151;
                    break;
                }
                case -2147483218: {
                    if (secLexema == 268435481 || secLexema == 268435486 || secLexema == 268435487 || secLexema == 268435506 || secLexema == 268435488 || secLexema == 268435490 || secLexema == 268435479 || secLexema == 268435508 || secLexema == 268435482 || secLexema == 268435484 || secLexema == 268435492 || secLexema == 268435510) {
                        numrole = 202;
                        break;
                    }
                    numrole = 203;
                    break;
                }
                case -2147483197: {
                    if (secLexema == 268435481 || secLexema == 268435486 || secLexema == 268435487 || secLexema == 268435506 || secLexema == 268435488 || secLexema == 268435490 || secLexema == 268435479 || secLexema == 268435508 || secLexema == 268435482 || secLexema == 268435484 || secLexema == 268435492 || secLexema == 268435510) {
                        numrole = 204;
                        break;
                    }
                    numrole = 205;
                    break;
                }
                case -2147483144: {
                    if (secLexema == 268435716 || secLexema == 268435706) {
                        numrole = 224;
                        break;
                    }
                    numrole = 225;
                    break;
                }
                case -2147483123: {
                    if (secLexema == 268435615) {
                        numrole = 234;
                        break;
                    }
                    numrole = 235;
                    break;
                }
                default: {
                    System.out.println("\n\u041f\u043e\u043c\u0438\u043b\u043a\u0430: \u0432\u0456\u0434\u0441\u0443\u0442\u043d\u044f \u043e\u0431\u0440\u043e\u0431\u043a\u0430:" + this.lang.getNonTerminals()[nontermcol] + this.lang.getLexemaText(this.lang.getNonTerminals()[nontermcol]));
                    return false;
                }
            }
        }
        if (this.parserUprTable[nontermcol][termCol] > 0) {
            numrole = this.parserUprTable[nontermcol][termCol];
        }
        int iitmp = 0;
        Node tmp = null;
        for (Node tmp1 : this.lang.getLanguarge()) {
            if (numrole != ++iitmp) continue;
            tmp = tmp1;
            break;
        }
        int[] pravilo = tmp.getRoole();
        for (int ii = 1; ii < pravilo.length; ++ii) {
            if (pravilo[ii] > 0) {
                if (pravilo[ii] == this.lexemaCode) {
                    this.lexemaCode = this.CppScaner();
                    continue;
                }
                if (this.lang.getLexemaText(pravilo[ii]).equals("else")) {
                    ii += 2;
                    continue;
                }
                System.out.println("\n\u041f\u0440\u043e\u043f\u0443\u0449\u0435\u043d\u0430 \u043b\u0435\u043a\u0441\u0435\u043c\u0430 :" + this.lang.getLexemaText(pravilo[ii]));
                return false;
            }
            if (this.localrecursive(pravilo[ii])) continue;
            return false;
        }
        return true;
    }

    private void setClassLitera(String uprString, int initCode) {
        for (int ii = 0; ii < uprString.length(); ++ii) {
            this.scanerUprTable[uprString.charAt((int)ii)] = initCode;
        }
    }

    private void setUngetLexema(int secLexema) {
        this.ungetLexemaCode = secLexema;
    }

    public String getLexemaText() {
        byte[] textLexema = new byte[this.lexemaLen];
        for (int ii = 0; ii < this.lexemaLen; ++ii) {
            textLexema[ii] = this.lexema[ii];
        }
        return new String(textLexema);
    }

    public int CppScaner() {
        int q = 0;
        this.lexemaLen = 0;
        int litera = 0;
        int lexClass = 0;
        int lexemaClass = 0;
        int litConst = 0;
        String errorLoc = "^[]{},?: ; \t\n\u0000";
        if (this.ungetLexemaCode != 0) {
            int tmpLexema = this.ungetLexemaCode;
            this.ungetLexemaCode = 0;
            return tmpLexema;
        }
        try {
            while (this.fs.ready()) {
                if (this.fileData == null) {
                    this.fileData = this.fs.readLine();
                    System.out.println(this.fileData);
                    ++this.lexemaLine;
                    this.lexemaPos = 0;
                }
                if (this.ungetChar != 0) {
                    litera = this.ungetChar;
                    this.ungetChar = 0;
                } else if (this.lexemaPos < this.fileData.length()) {
                    litera = this.fileData.charAt(this.lexemaPos);
                    ++this.lexemaPos;
                } else if (this.lexemaPos == this.fileData.length()) {
                    litera = 10;
                    ++this.lexemaPos;
                } else if (this.lexemaPos > this.fileData.length()) {
                    this.fileData = this.fs.readLine();
                    System.out.println(this.fileData);
                    ++this.lexemaLine;
                    this.lexemaPos = 0;
                    if (this.fileData.length() == 0) continue;
                    litera = this.fileData.charAt(this.lexemaPos);
                    ++this.lexemaPos;
                }
                lexClass = this.scanerUprTable[litera &= 255];
                switch (q) {
                    case 0: {
                        if (litera == 10) {
                            ++this.lexemaLine;
                            break;
                        }
                        if (litera == 9 || litera == 13 || litera == 32 || litera == 8) break;
                        ++this.lexemaNumb;
                        this.lexemaLen = 0;
                        switch (lexClass) {
                            case 1: {
                                this.lexema[this.lexemaLen++] = (byte)litera;
                                return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                            }
                            case 2: {
                                this.lexema[this.lexemaLen++] = (byte)litera;
                                lexemaClass = 1;
                                q = 21;
                                break;
                            }
                            case 3: {
                                this.lexema[this.lexemaLen++] = (byte)litera;
                                lexemaClass = 2;
                                if (litera == 48) {
                                    q = 31;
                                    break;
                                }
                                if (litera < 49 || litera > 57) break;
                                q = 35;
                            }
                        }
                        if (q != 0) break;
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        lexemaClass = 5;
                        if (litera == 35) {
                            q = 120;
                            break;
                        }
                        if (litera == 46) {
                            q = 45;
                            break;
                        }
                        if (litera == 47) {
                            q = 50;
                            break;
                        }
                        if (litera == 34) {
                            q = 60;
                            break;
                        }
                        if (litera == 39) {
                            litConst = 0;
                            q = 70;
                            break;
                        }
                        if (litera == 58) {
                            q = 101;
                            break;
                        }
                        if (litera == 45) {
                            q = 102;
                            break;
                        }
                        if (litera == 43) {
                            q = 103;
                            break;
                        }
                        if (litera == 124) {
                            q = 104;
                            break;
                        }
                        if (litera == 42) {
                            q = 105;
                            break;
                        }
                        if (litera == 62) {
                            q = 106;
                            break;
                        }
                        if (litera == 60) {
                            q = 108;
                            break;
                        }
                        if (litera == 61) {
                            q = 110;
                            break;
                        }
                        if (litera == 38) {
                            q = 111;
                            break;
                        }
                        if (litera == 33) {
                            q = 112;
                            break;
                        }
                        if (litera == 37) {
                            q = 113;
                            break;
                        }
                        if (litera == 94) {
                            q = 114;
                            break;
                        }
                        q = 11;
                        ++this.errors;
                        break;
                    }
                    case 120: {
                        if (litera == 92) {
                            q = 121;
                            break;
                        }
                        if (litera == 10) {
                            q = 0;
                            this.lexemaLen = 0;
                            break;
                        }
                        q = 120;
                        break;
                    }
                    case 121: {
                        if (litera == 10) {
                            q = 120;
                            break;
                        }
                        q = 121;
                        break;
                    }
                    case 11: {
                        if (errorLoc.indexOf(litera) < 0) break;
                        this.ungetChar = litera;
                        q = 0;
                        break;
                    }
                    case 70: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 39 && litConst == 0) {
                            System.out.println("\n \u0414\u043e\u0432\u0436\u0438\u043d\u0430 \u043b\u0456\u0442\u0435\u0440\u043d\u043e\u0457 \u043a\u043e\u043d\u0441\u0442\u0430\u043d\u0442\u0438 == 0");
                            return 268435464;
                        }
                        if (litera == 39 && litConst <= 2) {
                            return 268435464;
                        }
                        if (litera == 39 && litConst > 2) {
                            System.out.println("\n \u0414\u043e\u0432\u0436\u0438\u043d\u0430 \u043b\u0456\u0442\u0435\u0440\u043d\u043e\u0457 \u043a\u043e\u043d\u0441\u0442\u0430\u043d\u0442\u0438 >= 2");
                            return 268435464;
                        }
                        if (litera == 92) {
                            q = 73;
                            break;
                        }
                        ++litConst;
                        q = 70;
                        break;
                    }
                    case 73: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 116 || litera == 39 || litera == 34 || litera == 110 || litera == 114) {
                            ++litConst;
                            q = 70;
                            break;
                        }
                        if (litera == 88 || litera == 120) {
                            q = 75;
                            break;
                        }
                        if (litera >= 48 || litera <= 55) {
                            q = 79;
                            break;
                        }
                        ++litConst;
                        q = 70;
                        break;
                    }
                    case 75: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 65 && litera <= 70 || litera >= 97 && litera <= 102 || litera >= 48 && litera <= 57) {
                            q = 76;
                            break;
                        }
                        ++litConst;
                        q = 70;
                        break;
                    }
                    case 76: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 39) {
                            return 268435464;
                        }
                        if (litera >= 65 && litera <= 70 || litera >= 97 && litera <= 102 || litera >= 48 && litera <= 57) {
                            ++litConst;
                            q = 70;
                            break;
                        }
                        ++litConst;
                        q = 70;
                        break;
                    }
                    case 79: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 39) {
                            return 268435464;
                        }
                        if (litera >= 48 && litera <= 55) {
                            q = 80;
                            break;
                        }
                        ++litConst;
                        q = 70;
                        break;
                    }
                    case 80: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 39) {
                            return 268435464;
                        }
                        if (litera >= 48 && litera <= 55) {
                            ++litConst;
                            q = 70;
                            break;
                        }
                        ++litConst;
                        q = 70;
                        break;
                    }
                    case 21: {
                        if (lexClass == 2 || lexClass == 3) {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            break;
                        }
                        this.ungetChar = litera;
                        int itmp = this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                        if (itmp != -1) {
                            return itmp;
                        }
                        return 268435462;
                    }
                    case 31: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 55) {
                            q = 34;
                            break;
                        }
                        if (litera == 88 || litera == 120) {
                            q = 32;
                            break;
                        }
                        if (litera == 85 || litera == 117) {
                            q = 37;
                            break;
                        }
                        if (litera == 76 || litera == 108) {
                            q = 36;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 32: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57 || litera >= 65 && litera <= 70 || litera >= 97 && litera <= 102) {
                            q = 33;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        q = 0;
                        break;
                    }
                    case 33: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57 || litera >= 65 && litera <= 70 || litera >= 97 && litera <= 102) {
                            q = 33;
                            break;
                        }
                        if (litera == 76 || litera == 108) {
                            q = 36;
                            break;
                        }
                        if (litera == 85 || litera == 117) {
                            q = 37;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 34: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 55) {
                            q = 34;
                            break;
                        }
                        if (litera == 76 || litera == 108) {
                            q = 36;
                            break;
                        }
                        if (litera == 85 || litera == 117) {
                            q = 37;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 35: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57) {
                            q = 35;
                            break;
                        }
                        if (litera == 46) {
                            q = 40;
                            break;
                        }
                        if (litera == 69 || litera == 101) {
                            q = 42;
                            break;
                        }
                        if (litera == 76 || litera == 108) {
                            q = 36;
                            break;
                        }
                        if (litera == 85 || litera == 117) {
                            q = 37;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 36: {
                        if (litera == 85 || litera == 117) {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            return 268435470;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 37: {
                        if (litera == 76 || litera == 108) {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            return 268435470;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 40: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57) {
                            q = 41;
                            break;
                        }
                        if (litera == 69 || litera == 101) {
                            q = 42;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 41: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57) {
                            q = 41;
                            break;
                        }
                        if (litera == 69 || litera == 101) {
                            q = 42;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 42: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57) {
                            q = 44;
                            break;
                        }
                        if (litera == 43 || litera == 45) {
                            q = 43;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        q = 0;
                        break;
                    }
                    case 43: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57) {
                            q = 44;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        q = 0;
                        break;
                    }
                    case 44: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57) {
                            q = 44;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return 268435464;
                    }
                    case 45: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera >= 48 && litera <= 57) {
                            q = 41;
                            break;
                        }
                        if (litera == 69 || litera == 101) {
                            q = 42;
                            break;
                        }
                        if (litera == 46) {
                            q = 46;
                            break;
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 46: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 46) {
                            return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                        }
                        this.ungetChar = litera;
                        --this.lexemaLen;
                        q = 0;
                        break;
                    }
                    case 50: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 47) {
                            q = 51;
                            break;
                        }
                        if (litera == 42) {
                            q = 53;
                            break;
                        }
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 51: {
                        if (litera != 10) {
                            q = 51;
                            break;
                        }
                        q = 0;
                        break;
                    }
                    case 53: {
                        if (litera == 42) {
                            q = 54;
                            break;
                        }
                        q = 53;
                        break;
                    }
                    case 54: {
                        if (litera == 42) {
                            q = 54;
                            break;
                        }
                        if (litera == 47) {
                            q = 0;
                            break;
                        }
                        q = 53;
                        break;
                    }
                    case 60: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 34) {
                            return 268435466;
                        }
                        if (litera == 92) {
                            q = 62;
                            break;
                        }
                        q = 60;
                        break;
                    }
                    case 62: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        q = 60;
                        break;
                    }
                    case 101: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 58) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 102: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 45 && litera != 61 && litera != 62) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 103: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 43 && litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 104: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 124 && litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 105: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 106: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 62) {
                            q = 107;
                            break;
                        }
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 107: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 108: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera == 60) {
                            q = 109;
                            break;
                        }
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 109: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 110: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 111: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 38 && litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 112: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 113: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                    case 114: {
                        this.lexema[this.lexemaLen++] = (byte)litera;
                        if (litera != 61) {
                            this.ungetChar = litera;
                            --this.lexemaLen;
                        }
                        return this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    }
                }
            }
            if (this.lexemaLen == 0) {
                return -1;
            }
            switch (lexemaClass) {
                case 1: {
                    int itmp1 = this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                    if (itmp1 != -1) {
                        return itmp1;
                    }
                    return 268435462;
                }
                case 2: {
                    return 268435464;
                }
                case 3: {
                    return -1;
                }
                case 4: {
                    return 268435464;
                }
                case 5: {
                    this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                }
            }
            return -1;
        }
        catch (IOException ee) {
            System.out.print("\u041f\u043e\u043c\u0438\u043b\u043a\u0430 \u0432\u0432\u043e\u0434\u0443-\u0432\u0438\u0432\u043e\u0434\u0443: " + ee.toString());
            return -1;
        }
    }
}