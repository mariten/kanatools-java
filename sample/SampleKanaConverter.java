import com.mariten.kanatools.KanaConverter;

class SampleKanaConverter
{
    public static void main(String[] args)
    {
        // Check for arg input
        if(args.length < 1) {
            System.out.println("Must specify a term to convert");
            System.out.println("EXAMPLE:");
            System.out.println("  java SampleKanaConverter ﾃｽﾄ");
            return;
        }
        String input_string = args[0];

        // Define conversion ops
        int sample_conversion_ops = 0;
        sample_conversion_ops |= KanaConverter.OP_HAN_KATA_TO_ZEN_KATA;
        sample_conversion_ops |= KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA;
        sample_conversion_ops |= KanaConverter.OP_ZEN_ASCII_TO_HAN_ASCII;

        // Convert and print
        String output_string = KanaConverter.convertKana(input_string, sample_conversion_ops);
        String output_string1 = KanaConverter.convertKana(input_string, sample_conversion_ops);
        String output_string2 = KanaConverter.convertKana(input_string, sample_conversion_ops);
        int sample_conversion_ops1 = 0;
        int sample_conversion_ops2 = 0;
        System.out.println("dummy supress");
        int sample_conversion_ops5 = 0;
        int sample_conversion_ops6 = 0;
        System.out.println(output_string+"test123");
        int sample_conversion_ops3 = 0;
        int sample_conversion_ops4 = 0;
    }
}
