public class ShiftDemo {
    public static void main(String[] args) {

        byte b = (byte) 0b00000001; // example
        /*
         * convert to unsigned 0..255; 0xFF in Int32 is 00000000 00000000 00000000
         * 11111111
         * 
         */
        int u = b & 0xFF;

        String bits = String.format("%8s", Integer.toBinaryString(u)).replace(' ',
                '0');
        System.out.println(bits); // 00000001

        // Shifting b

        // Shifting 0 times -> no effect
        u = b << 0;

        bits = String.format("%8s", Integer.toBinaryString(u)).replace(' ',
                '0');
        System.out.println("Shifting 00000001 0 times: " + bits); // 00000001

        // Shifting 1 times -> 00000010
        u = b << 1;

        bits = String.format("%8s", Integer.toBinaryString(u)).replace(' ',
                '0');
        System.out.println("Shifting 00000001 1 times: " + bits); // 00000010

        // Shifting 2 times -> 00000010
        u = b << 2;

        bits = String.format("%8s", Integer.toBinaryString(u)).replace(' ',
                '0');
        System.out.println("Shifting 00000001 2 times: " + bits); // 00000100
    }
}
