package lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Stack;

public class PascalParcer {
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
    private int maxLenRecord;

    public PascalParcer(MyLang lang0, String fname) throws IOException {
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
        this.maxLenRecord = 0;
        for (int ii = 0; ii < this.scanerUprTable.length; ++ii) {
            this.scanerUprTable[ii] = 0;
        }
        this.setClassLitera("()[];,+-^=*", 1);
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
        this.lexemaCode = this.pascalScaner();
        while (!ss.empty()) {
            int termCol;
            int ssItem = (Integer)ss.peek();
            if (ssItem > 0) {
                if (ssItem == this.lexemaCode) {
                    ss.pop();
                    this.lexemaCode = this.pascalScaner();
                    continue;
                }
                if (this.lang.getLexemaText(ssItem).equals("else")) {
                    ss.pop();
                    ss.pop();
                    continue;
                }
                System.out.println("\n\u041f\u0440\u043e\u043f\u0443\u0449\u0435\u043d\u0430 \u043b\u0435\u043a\u0441\u0435\u043c\u0430 :" + this.lang.getLexemaText(ssItem));
                do {
                    this.lexemaCode = this.pascalScaner();
                } while (this.lexemaCode != -1);
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
                do {
                    this.lexemaCode = this.pascalScaner();
                } while (this.lexemaCode != -1);
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
            numrole = this.parserUprTable[nontermcol][termCol];
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
        this.lexemaCode = this.pascalScaner();
        if (this.localrecursive(this.lang.getAxioma())) {
            System.out.println("\n\u041f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0447\u043d\u043e \u0432\u0456\u0440\u043d\u0430");
        } else {
            while (this.lexemaCode != -1) {
                this.lexemaCode = this.pascalScaner();
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
        numrole = this.parserUprTable[nontermcol][termCol];
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
                    this.lexemaCode = this.pascalScaner();
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

    public int pascalScaner() {
        int q = 0;
        this.lexemaLen = 0;
        int litera = 0;
        int lexClass = 0;
        int lexemaClass = 0;
        boolean litConst = false;
        String errorLoc = "^[]{},?: ; \t\n\u0000";
        try {
            while (this.fs.ready() || this.lexemaPos <= this.maxLenRecord) {
                if (this.fileData == null) {
                    this.fileData = this.fs.readLine();
                    System.out.println(this.fileData);
                    ++this.lexemaLine;
                    this.lexemaPos = 0;
                    this.maxLenRecord = this.fileData.length();
                }
                while (this.ungetChar != 0 || this.lexemaPos <= this.maxLenRecord) {
                    if (this.ungetChar != 0) {
                        litera = this.ungetChar;
                        this.ungetChar = 0;
                    } else {
                        if (this.lexemaPos < this.fileData.length()) {
                            litera = this.fileData.charAt(this.lexemaPos);
                        }
                        if (this.lexemaPos == this.fileData.length()) {
                            litera = 10;
                        }
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
                                    q = 31;
                                }
                            }
                            if (q != 0) break;
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            lexemaClass = 5;
                            if (litera == 46) {
                                q = 4;
                                break;
                            }
                            if (litera == 58) {
                                q = 5;
                                break;
                            }
                            if (litera == 47) {
                                q = 6;
                                break;
                            }
                            if (litera == 62) {
                                q = 7;
                                break;
                            }
                            if (litera == 60) {
                                q = 8;
                                break;
                            }
                            if (litera == 33) {
                                q = 9;
                                break;
                            }
                            if (litera == 39) {
                                q = 10;
                                break;
                            }
                            q = 11;
                            ++this.errors;
                            break;
                        }
                        case 21: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (lexClass == 2 || lexClass == 3) break;
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            int tmp1 = this.lang.getLexemaCode(this.lexema, this.lexemaLen, 0);
                            if (tmp1 == -1) {
                                return 268435470;
                            }
                            return tmp1;
                        }
                        case 7: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera == 61) {
                                return 268435683;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435681;
                        }
                        case 8: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera == 61) {
                                return 268435684;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435680;
                        }
                        case 9: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera == 61) {
                                return 268435682;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            q = 11;
                            break;
                        }
                        case 5: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera == 61) {
                                return 268435577;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435530;
                        }
                        case 6: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera == 42) {
                                q = 61;
                                break;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435710;
                        }
                        case 61: {
                            if (litera != 42) break;
                            q = 62;
                            break;
                        }
                        case 62: {
                            if (litera == 47) {
                                q = 0;
                                this.lexemaLen = 0;
                                break;
                            }
                            q = 61;
                            break;
                        }
                        case 4: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera >= 48 && litera <= 57) {
                                q = 32;
                                break;
                            }
                            if (litera == 46) {
                                return 268435741;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435464;
                        }
                        case 31: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera >= 48 && litera <= 57) break;
                            if (litera == 46) {
                                q = 32;
                                break;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435468;
                        }
                        case 32: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera >= 48 && litera <= 57) break;
                            if (litera == 101 || litera == 69) {
                                q = 33;
                                break;
                            }
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435468;
                        }
                        case 33: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera >= 48 && litera <= 57) {
                                q = 35;
                                break;
                            }
                            if (litera == 43 || litera <= 45) {
                                q = 34;
                                break;
                            }
                            q = 11;
                            break;
                        }
                        case 34: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera >= 48 && litera <= 57) {
                                q = 35;
                                break;
                            }
                            q = 11;
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            break;
                        }
                        case 35: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera >= 48 && litera <= 57) break;
                            this.ungetChar = litera;
                            --this.lexemaLen;
                            return 268435468;
                        }
                        case 11: {
                            if (errorLoc.indexOf(litera) < 0) break;
                            this.ungetChar = litera;
                            q = 0;
                            break;
                        }
                        case 10: {
                            this.lexema[this.lexemaLen++] = (byte)litera;
                            if (litera != 39) break;
                            return 268435472;
                        }
                    }
                }
                this.fileData = null;
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