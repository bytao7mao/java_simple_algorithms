
char A = (char)Integer.valueOf("01000001", 2).intValue();
char B = (char)Integer.valueOf("01000010", 2).intValue();
char C = (char)Integer.valueOf("01000011", 2).intValue();
char D = (char)Integer.valueOf("01100100", 2).intValue();
char E = (char)Integer.valueOf("01100101", 2).intValue();
char F = (char)Integer.valueOf("01100110", 2).intValue();
char G = (char)Integer.valueOf("01100111", 2).intValue();
char H = (char)Integer.valueOf("01101000", 2).intValue();
char I = (char)Integer.valueOf("01101001", 2).intValue();
char J = (char)Integer.valueOf("01101010", 2).intValue();
char K = (char)Integer.valueOf("01101011", 2).intValue();
char L = (char)Integer.valueOf("01101100", 2).intValue();
char M = (char)Integer.valueOf("01101101", 2).intValue();
char N = (char)Integer.valueOf("01101110", 2).intValue();
char O = (char)Integer.valueOf("01101111", 2).intValue();
char P = (char)Integer.valueOf("01110000", 2).intValue();
char R = (char)Integer.valueOf("01010010", 2).intValue();
char S = (char)Integer.valueOf("01010011", 2).intValue();
char T = (char)Integer.valueOf("01010100", 2).intValue();
char U = (char)Integer.valueOf("01010101", 2).intValue();
char V = (char)Integer.valueOf("01010110", 2).intValue();
char W = (char)Integer.valueOf("01010111", 2).intValue();
char X = (char)Integer.valueOf("01011000", 2).intValue();
char Y = (char)Integer.valueOf("01011001", 2).intValue();
char Z = (char)Integer.valueOf("01011010", 2).intValue();

// ASCII TABLE:
// Dec Hex	Binary	Character Description
// 65	41	01000001	A	 
// 66	42	01000010	B	 
// 67	43	01000011	C	 
// 68	44	01000100	D	 
// 69	45	01000101	E	 
// 70	46	01000110	F	 
// 71	47	01000111	G	 
// 72	48	01001000	H	 
// 73	49	01001001	I	 
// 74	4A	01001010	J	 
// 75	4B	01001011	K	 
// 76	4C	01001100	L	 
// 77	4D	01001101	M	 
// 78	4E	01001110	N	 
// 79	4F	01001111	O	 
// 80	50	01010000	P	 
// 81	51	01010001	Q	 
// 82	52	01010010	R	 
// 83	53	01010011	S	 
// 84	54	01010100	T	 
// 85	55	01010101	U	 
// 86	56	01010110	V	 
// 87	57	01010111	W	 
// 88	58	01011000	X	 
// 89	59	01011001	Y	 
// 90	5A	01011010	Z	 
// 91	5B	01011011	[	left square bracket
// 92	5C	01011100	\	backslash
// 93	5D	01011101	]	right square bracket
// 94	5E	01011110	^	caret / circumflex
// 95	5F	01011111	_	underscore
// 96	60	01100000	`	grave / accent
// 97	61	01100001	a	 
// 98	62	01100010	b	 
// 99	63	01100011	c	 
// 100	64	01100100	d	 
// 101	65	01100101	e	 
// 102	66	01100110	f	 
// 103	67	01100111	g	 
// 104	68	01101000	h	 
// 105	69	01101001	i	 
// 106	6A	01101010	j	 
// 107	6B	01101011	k	 
// 108	6C	01101100	l	 
// 109	6D	01101101	m	 
// 110	6E	01101110	n	 
// 111	6F	01101111	o	 
// 112	70	01110000	p	 
// 113	71	01110001	q	 
// 114	72	01110010	r	 
// 115	73	01110011	s	 
// 116	74	01110100	t	 
// 117	75	01110101	u	 
// 118	76	01110110	v	 
// 119	77	01110111	w	 
// 120	78	01111000	x	 
// 121	79	01111001	y	 
// 122	7A	01111010	z

//info here: https://www.asciitable.com/



//messageFromBinaryCode
String messageFromBinaryCode(String code) {
    String s2 = "";   
    char nextChar;

    for(int i = 0; i <= code.length()-8; i += 8) 
    {
         nextChar = (char)Integer.parseInt(code.substring(i, i+8), 2);
         s2 += nextChar;
    }
    return s2;
}
