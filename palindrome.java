boolean checkPalindrome(String inputString) {
    return inputString.equals(new StringBuilder(inputString).reverse().toString());
}
