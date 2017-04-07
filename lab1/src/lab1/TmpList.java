package lab1;


import java.util.Iterator;

import java.util.Iterator;

 class TmpList
{
    TmpList treeFather;
    Node treeNode;
    int treePos;
    
    private TmpList(final TmpList tmpFather, final Node tmpNode, final int tmpPos) {
        this.treeFather = tmpFather;
        this.treeNode = tmpNode;
        this.treePos = tmpPos;
    }
    
    private void destroy() {
        this.treeFather = null;
        this.treeNode = null;
        this.treePos = 0;
    }
    
    private boolean roleInTree(final Node tmp) {
        TmpList tmpLst;
        for (tmpLst = null, tmpLst = this; tmpLst != null; tmpLst = tmpLst.treeFather) {
            if (tmpLst.treeNode.getRoole()[0] == tmp.getRoole()[0]) {
                return true;
            }
        }
        return false;
    }
    
    private boolean searchLeftRecursion(final MyLang lang) {
        final int[] epsilon = lang.getEpsilonNonterminals();
        TmpList tmpLst;
        for (tmpLst = null, tmpLst = this; tmpLst.treeFather != null; tmpLst = tmpLst.treeFather) {}
        final Node root = tmpLst.treeNode;
        if (root.getRoole()[0] == this.treeNode.getRoole()[this.treePos]) {
            System.out.println("\u041b\u0456\u0432\u043e\u0440\u0435\u043a\u0443\u0440\u0441\u0438\u0432\u043d\u0438\u0439 \u0432\u0438\u0432\u043e\u0434");
            this.printLeftRecurion(lang);
            return true;
        }
        int count = 0;
        for (final Node tmp : lang.getLanguarge()) {
            ++count;
            if (tmp.getTeg() == 1) {
                continue;
            }
            if (this.roleInTree(tmp)) {
                continue;
            }
            final int[] role = tmp.getRoole();
            if (role[0] != this.treeNode.getRoole()[this.treePos]) {
                continue;
            }
            for (int ii = 1; ii < role.length; ++ii) {
                if (role[ii] > 0) {
                    break;
                }
                final TmpList tree1 = new TmpList(this, tmp, ii);
                if (tree1.searchLeftRecursion(lang)) {
                    tree1.destroy();
                    return true;
                }
                tree1.destroy();
                int ii2;
                for (ii2 = 0; ii2 < epsilon.length && epsilon[ii2] != role[ii]; ++ii2) {}
                if (ii2 == epsilon.length) {
                    break;
                }
            }
        }
        return false;
    }
    
    private void printLeftRecurion(final MyLang lang) {
        if (this.treeFather != null) {
            final TmpList tmp = this.treeFather;
            tmp.printLeftRecurion(lang);
        }
        final int[] tmpRole = this.treeNode.getRoole();
        for (int ii = 0; ii < tmpRole.length; ++ii) {
            if (ii == 0) {
                System.out.print(lang.getLexemaText(tmpRole[ii]) + " -> ");
            }
            else {
                System.out.print(lang.getLexemaText(tmpRole[ii]) + " ");
            }
        }
        System.out.println();
        for (int ii = 1; ii < this.treePos; ++ii) {
            System.out.println(lang.getLexemaText(tmpRole[ii]) + " =>* epsilon");
        }
    }
    
    private boolean searchRigthRecursion(final MyLang lang) {
        final int[] epsilon = lang.getEpsilonNonterminals();
        TmpList tmpLst;
        for (tmpLst = this; tmpLst.treeFather != null; tmpLst = tmpLst.treeFather) {}
        final Node root = tmpLst.treeNode;
        if (root.getRoole()[0] == this.treeNode.getRoole()[this.treePos]) {
            System.out.println("\n\u041f\u0440\u0430\u0432\u043e\u0440\u0435\u043a\u0443\u0440\u0441\u0438\u0432\u043d\u0438\u0439 \u0432\u0438\u0432\u043e\u0434:");
            this.printRigthRecurion(lang);
            return true;
        }
        int count = 0;
        for (final Node tmp : lang.getLanguarge()) {
            ++count;
            if (tmp.getTeg() == 1) {
                continue;
            }
            if (this.roleInTree(tmp)) {
                continue;
            }
            final int[] role = tmp.getRoole();
            if (role.length <= 1) {
                continue;
            }
            if (role[role.length - 1] > 0) {
                continue;
            }
            if (role[0] != this.treeNode.getRoole()[this.treePos]) {
                continue;
            }
            for (int ii = role.length - 1; ii > 0; --ii) {
                if (role[ii] > 0) {
                    break;
                }
                final TmpList tree1 = new TmpList(this, tmp, ii);
                if (tree1.searchRigthRecursion(lang)) {
                    tree1.destroy();
                    return true;
                }
                tree1.destroy();
                int ii2;
                for (ii2 = 0; ii2 < epsilon.length && epsilon[ii2] != role[ii]; ++ii2) {}
                if (ii2 == epsilon.length) {
                    break;
                }
            }
        }
        return false;
    }
    
    private void printRigthRecurion(final MyLang lang) {
        if (this.treeFather != null) {
            final TmpList tmp = this.treeFather;
            tmp.printRigthRecurion(lang);
        }
        final int[] tmpRole = this.treeNode.getRoole();
        for (int ii = 0; ii < tmpRole.length; ++ii) {
            if (ii == 0) {
                System.out.print(lang.getLexemaText(tmpRole[ii]) + " -> ");
            }
            else {
                System.out.print(lang.getLexemaText(tmpRole[ii]) + " ");
            }
        }
        System.out.println();
        for (int ii = tmpRole.length - 1; ii > this.treePos; --ii) {
            System.out.println(lang.getLexemaText(tmpRole[ii]) + " =>* epsilon");
        }
    }
}