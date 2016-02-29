package mariten.kanatools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;
import mariten.kanatools.KanaConverter;
import static org.junit.Assert.*;

public abstract class KanaConverterTester
{
    /** Takes in property (specified in Ant's build.xml) indicating whether to perform actual PHP testing or not */
    protected boolean do_direct_php_testing;

    /** Use this constant for tests that should never perform actual PHP testing */
    protected static final boolean NEVER_TEST_IN_PHP = false;

    public KanaConverterTester()
    {
        // Determine whether PHP testing was requested by Ant
        String php_option_from_ant = System.getProperty("test_with_php");
        if(php_option_from_ant != null
        && php_option_from_ant.equals("yes")) {
            do_direct_php_testing = true;
        } else {
            do_direct_php_testing = false;
        }

        // Test instantiation
        KanaConverter kana_converter_object = new KanaConverter();
    }


    //{{{ void assertConverted(int, boolean, String, String)
    /**
      * Convert an input string using KanaConverter and assert it matches its expected result
      *
      * @param  conv_flags        Flag-based integer of conversion options for use by convertKana function
      * @param  execute_php_test  Also check that these strings match each other using PHP's "mb_convert_kana" function?
      * @param  str_to_convert    String to test (pass to convertKana function)
      * @param  expected_result   Expected results of convertKana function
      */
    protected void assertConverted(int conv_flags, boolean execute_php_test, String str_to_convert, String expected_result)
    {
        assertEquals(expected_result, KanaConverter.convertKana(str_to_convert, conv_flags));
        if(execute_php_test) {
            assertConvertedUsingPHP(conv_flags, str_to_convert, expected_result);
        }
    }
    //}}}
    //{{{ void assertConverted(String, boolean, String, String)
    protected void assertConverted(String conv_flags_string, boolean execute_php_test, String str_to_convert, String expected_result)
    {
        assertEquals(expected_result, KanaConverter.convertKana(str_to_convert, conv_flags_string));
        if(execute_php_test) {
            int conv_flags = -1;
            try {
                // Translate int-flag op format to PHP-style string op format using restricted function in KanaConverter
                Class<?> target_class = Class.forName("mariten.kanatools.KanaConverter");
                Method restricted_converter = target_class.getDeclaredMethod("createOpsArrayFromString", String.class);
                restricted_converter.setAccessible(true);
                Object function_result = restricted_converter.invoke(null, conv_flags_string);
                conv_flags = (Integer)function_result;
            } catch (Exception ex) {
                fail("Reflection failed: " + ex.getMessage());
            }

            assertConvertedUsingPHP(conv_flags, str_to_convert, expected_result);
        }
    }
    //}}}
    //{{{ void assertConverted(int, String, String)
    protected void assertConverted(int conv_flags, String str_to_convert, String expected_result)
    {
        this.assertConverted(conv_flags, this.do_direct_php_testing, str_to_convert, expected_result);
    }
    //}}}
    //{{{ void assertConverted(String, String, String)
    protected void assertConverted(String conv_flags_string, String str_to_convert, String expected_result)
    {
        this.assertConverted(conv_flags_string, this.do_direct_php_testing, str_to_convert, expected_result);
    }
    //}}}
    //{{{ void assertConvertedWithExclusions(int, String, String, String)
    /**
      * Like "assertConverted()", however allow passing excluded characters and do not test in PHP
      *
      * @param  conv_flags        Flag-based integer of conversion options for use by convertKana function
      * @param  chars_to_ignore   Characters to exclude from conversions
      * @param  str_to_convert    String to test (pass to convertKana function)
      * @param  expected_result   Expected results of convertKana function
      */
    protected void assertConvertedWithExclusions(int conv_flags, String chars_to_ignore, String str_to_convert, String expected_result)
    {
        assertEquals(expected_result, KanaConverter.convertKana(str_to_convert, conv_flags, chars_to_ignore));
    }
    //}}}


    //{{{ void assertConvertedUsingPHP(int, String, String)
    /**
      * Perform an actual call to PHP's "mb_convert_kana" function and assert the converted string matches the expected string.
      *
      * @param  conv_flags       flag-based integer containing conversion methods (will be converted to PHP-style string)
      * @param  str_to_convert   String to pass to PHP for conversion by "mb_convert_kana"
      * @param  expected_result  Expected string output from PHP
      */
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
        php_result = processPhpResult(php_result, conv_flags);
        assertEquals(expected_result, php_result);
    }
    //}}}


    //{{{ String makeOperationStringForPHP(int)
    /**
      * Convert a KanaConverter flag-based integer set of operations to a PHP-style operation string.
      *
      * @param  conv_flags  Flag-based integer to convert
      * @return Operations string for PHP's "mb_convert_kana" (the "$option" parameter)
      */
    protected String makeOperationStringForPHP(int conv_flags)
    {
        if(conv_flags <= 0) {
            return "";
        }

        StringBuilder php_conv_flags = new StringBuilder();
        boolean has_han2zen_kana_conversion = false;
        for(Entry<Character, Integer> op_map_item : KanaConverter.LETTER_OP_CODE_LOOKUP.entrySet()) {
            char op_char = op_map_item.getKey();
            int  op_flag = op_map_item.getValue();
            if((conv_flags & op_flag) != 0) {
                php_conv_flags.append(op_char);
                switch(op_char) {
                case 'A':
                    // Also convert spaces, for testing compatibility with mb_convert_kana
                    php_conv_flags.append('S');
                    break;
                case 'a':
                    // Also convert spaces, for testing compatibility with mb_convert_kana
                    php_conv_flags.append('s');
                    break;
                case 'H':
                case 'K':
                    has_han2zen_kana_conversion = true;
                }
            }
        }

        if(has_han2zen_kana_conversion
        && ((conv_flags & KanaConverter.OP_KEEP_DIACRITIC_MARKS_APART) == 0)) {
            // For testing compatibility with mb_convert_kana, always add "V" unless
            // no-collapse was specifically requested.
            // Added this after making diacritic-collapse default in KanaConverter
            php_conv_flags.append('V');
        }

        return php_conv_flags.toString();
    }
    //}}}


    //{{{ String processPhpResult(String, int)
    /**
      * Add special logic to account for differences between this library and PHP's mb_convert_kana.
      * @note   PHP's "mb_convert_kana" ignores conversion of quote, double-quote, backslash, and tilde
      *
      * @param  php_raw_result  Actual result (output) of execution of mb_convert_kana
      * @param  conv_flags      Flag-based integer to convert
      * @result String updated to conform to the rules of this library
      */
    protected String processPhpResult(String php_raw_result, int conv_flags)
    {
        String php_processed_result = php_raw_result;
        if(0 != (conv_flags & KanaConverter.OP_HAN_ASCII_TO_ZEN_ASCII)) {
            php_processed_result = php_processed_result
            .replace('\'', '＇')
            .replace('\"', '＂')
            .replace('\\', '＼')
            .replace('~', '～');
        }
        if(0 != (conv_flags & KanaConverter.OP_ZEN_ASCII_TO_HAN_ASCII)) {
            php_processed_result = php_processed_result
            .replace('＇', '\'')
            .replace('＂', '\"')
            .replace('＼', '\\')
            .replace('～', '~');
        }

        return php_processed_result;
    }
    //}}}
}
