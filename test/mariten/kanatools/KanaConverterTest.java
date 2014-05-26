package mariten.kanatools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import mariten.kanatools.KanaConverter;
import org.junit.Test;
import static org.junit.Assert.*;

public class KanaConverterTest
{
    private KanaConverter convert_tester = new KanaConverter();


    //{{{ testErrorCases()
    @Test
    public void testErrorCases()
    {
        assertNull(convert_tester.mbConvertKana(null, "C", "utf8"));
    }
    //}}}


    //{{{ 'N': testHankakuNumberToZenkakuNumber()
    @Test
    public void testHankakuNumberToZenkakuNumber()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER,
            "/0123456789:",
            "/０１２３４５６７８９:"
        );
    }
    //}}}


    //{{{ 'n': testZenkakuNumberToHankakuNumber()
    @Test
    public void testZenkakuNumberToHankakuNumber()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER,
            "／０１２３４５６７８９：",
            "／0123456789："
        );
    }
    //}}}


    //{{{ assertConverted()
    private void assertConverted(String conv_options, String str_to_convert, String expected_result)
    {
        assertEquals(expected_result, convert_tester.mbConvertKana(str_to_convert, conv_options, "utf8"));
        assertConvertedUsingPHP(conv_options, str_to_convert, expected_result);
    }
    private void assertConverted(char conv_option, String str_to_convert, String expected_result) {
        assertConverted(String.valueOf(conv_option), str_to_convert, expected_result);
    }
    //}}}


    //{{{ assertConvertedUsingPHP()
    private void assertConvertedUsingPHP(String conv_options, String str_to_convert, String expected_result)
    {
        // Create shell command with each param as element of String array
        String[] command_for_php = new String[] {
            "php",
            "-r",
            "echo(mb_convert_kana('" + str_to_convert + "', '" + conv_options + "', 'UTF-8'));"
        };

        Process php_process = null;
        String php_result = "";
        try {
            // Execute command
            php_process = Runtime.getRuntime().exec(command_for_php);
            php_process.waitFor();

            // Get standard output from executed command
            BufferedReader process_output = new BufferedReader(new InputStreamReader(php_process.getInputStream()));
            php_result = process_output.readLine();
        } catch(Exception e) {
            System.out.println("Exception running PHP command:");
            System.out.println(e.getStackTrace().toString());
        }

        // Check results
        assertEquals(expected_result, php_result);
    }
    //}}}
}
