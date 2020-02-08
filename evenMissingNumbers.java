int a[] = {2,4,6,8,10,14};

    int expected = 2;
    for (int val : a) {
        if (expected != val) {
            System.out.println("Missing number is " + expected);
        }
        expected = expected +2;
    }
