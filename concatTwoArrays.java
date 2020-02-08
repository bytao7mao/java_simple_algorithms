public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;
        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType()/*String.class*/, aLen + bLen);
        System.arraycopy(a/*from*/, 0/*starting with index*/,
                c/*where to copy*/, 0/*starting with index in the destination array*/,
                /*total no. of components to be copied*/aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
