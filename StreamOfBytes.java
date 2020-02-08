//Stream of bytes
int filesize = (int) file.length(); //figure out the filesize
System.out.println(filesize);
byte[] data = new byte[filesize]; //create an array that is big enough
//create a stream to read the file
DataInputStream in = new DataInputStream(new FileInputStream(file));
in.readFully(data); //read file contents into array of binary chars
System.out.println(Arrays.toString(data));
in.close();