package mariten.kanatools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import mariten.kanatools.KanaConverter;
import static org.junit.Assert.*;

public abstract class KanaConverterTester
{
    protected static final boolean NEVER_TEST_IN_PHP = false;
    protected boolean do_direct_php_testing;

    public KanaConverterTester()
    {
        String php_option_from_ant = System.getProperty("test_with_php");
        if(php_option_from_ant != null
        && php_option_from_ant.equals("yes")) {
            do_direct_php_testing = true;
        } else {
            do_direct_php_testing = false;
        }
    }


    //{{{ assertConverted()
    protected void assertConverted(int conv_flags, boolean execute_php_test, String str_to_convert, String expected_result)
    {
        assertEquals(expected_result, KanaConverter.mbConvertKana(str_to_convert, conv_flags));
        if(execute_php_test) {
            assertConvertedUsingPHP(conv_flags, str_to_convert, expected_result);
        }
    }
    protected void assertConverted(String conv_flags_string, boolean execute_php_test, String str_to_convert, String expected_result)
    {
        int conv_flags = KanaConverter.createOpsArrayFromString(conv_flags_string);
        this.assertConverted(conv_flags, execute_php_test, str_to_convert, expected_result);
    }
    protected void assertConverted(int conv_flags, String str_to_convert, String expected_result)
    {
        this.assertConverted(conv_flags, this.do_direct_php_testing, str_to_convert, expected_result);
    }
    protected void assertConverted(String conv_flags_string, String str_to_convert, String expected_result)
    {
        this.assertConverted(conv_flags_string, this.do_direct_php_testing, str_to_convert, expected_result);
    }
    //}}}


    //{{{ assertConvertedUsingPHP()
    protected void assertConvertedUsingPHP(int conv_flags, String str_to_convert, String expected_result)
    {
        // Use single quotes in PHP code, so "encode" single quotes in string input
        // Break up input string and concatenate a single quote character using PHP syntax
        String str_to_convert_for_php = str_to_convert.replace("'", "' . \"'\" . '");

        // Create PHP string indicating conversion operations
        String conv_ops_for_php = makeOperationStringForPHP(conv_flags);

        // Create shell command with each param as element of String array
        String[] command_for_php = new String[] {
            "php",
            "-r",
            "echo(mb_convert_kana('" + str_to_convert_for_php + "', '" + conv_ops_for_php + "', 'UTF-8'));"
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
            if(php_result == null) {
                php_result = "";
            }
        } catch(Exception e) {
            System.out.println("Exception running PHP command:");
            System.out.println(e.getStackTrace().toString());
        }

        // Check results
        assertEquals(expected_result, php_result);
    }
    //}}}


    //{{{ makeOperationStringForPHP()
    protected String makeOperationStringForPHP(int conv_flags)
    {
        if(conv_flags <= 0) {
            return "";
        }

        StringBuilder php_conv_flags = new StringBuilder();
        for(Entry<Character, Integer> op_map_item : KanaConverter.LETTER_OP_CODE_LOOKUP.entrySet()) {
            char op_char = op_map_item.getKey();
            int  op_flag = op_map_item.getValue();
            if((conv_flags & op_flag) != 0) {
                php_conv_flags.append(op_char);
            }
        }

        return php_conv_flags.toString();
    }
    //}}}
}
