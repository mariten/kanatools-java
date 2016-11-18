package com.mariten.kanatools;
import com.mariten.kanatools.KanaConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * Provides automatic back-and-forth translation between Japanese kana and their romanized equivalents
  */
public class KanaRomanizer
{
    protected static final int MAX_SLICE_SIZE = 3;

    //{{{ Define all standard kana romanizations
    protected static final Map<String, String> STANDARD_KANA_ROMANIZATION_LOOKUP;
    static {
        // Unvoiced kana (standard gojuon)
        STANDARD_KANA_ROMANIZATION_LOOKUP = new HashMap<String, String>();
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ア", "a");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("イ", "i");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ウ", "u");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("エ", "e");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("オ", "o");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("カ", "ka");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("キ", "ki");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ク", "ku");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ケ", "ke");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("コ", "ko");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("サ", "sa");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("シ", "shi");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ス", "su");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("セ", "se");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ソ", "so");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("タ", "ta");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("チ", "chi");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ツ", "tsu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("テ", "te");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ト", "to");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ナ", "na");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ニ", "ni");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヌ", "nu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ネ", "ne");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ノ", "no");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ハ", "ha");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヒ", "hi");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("フ", "fu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヘ", "he");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ホ", "ho");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("マ", "ma");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ミ", "mi");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ム", "mu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("メ", "me");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("モ", "mo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヤ", "ya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ユ", "yu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヨ", "yo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ラ", "ra");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("リ", "ri");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ル", "ru");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("レ", "re");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ロ", "ro");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ワ", "wa");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヲ", "wo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ン", "n");

        // Voiced kana
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ガ", "ga");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ギ", "gi");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("グ", "gu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ゲ", "ge");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ゴ", "go");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ザ", "za");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ジ", "ji");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ズ", "zu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ゼ", "ze");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ゾ", "zo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ダ", "da");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヂ", "ji");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヅ", "zu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("デ", "de");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ド", "do");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("バ", "ba");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ビ", "bi");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ブ", "bu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ベ", "be");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ボ", "bo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("パ", "pa");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ピ", "pi");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("プ", "pu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ペ", "pe");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ポ", "po");

        // Combined y-sounds (unvoiced)
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("キャ", "kya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("キュ", "kyu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("キョ", "kyo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("シャ", "sha");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("シュ", "shu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ショ", "sho");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("チャ", "cha");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("チュ", "chu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("チョ", "cho");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヒャ", "hya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヒュ", "hyu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヒョ", "hyo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ミャ", "mya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ミュ", "myu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ミョ", "myo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("リャ", "rya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("リュ", "ryu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("リョ", "ryo");

        // Combined y-sounds (voiced)
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ギャ", "gya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ギュ", "gyu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ギョ", "gyo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ジャ", "ja");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ジュ", "ju");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ジョ", "jo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヂャ", "ja");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヂュ", "ju");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ヂョ", "jo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ビャ", "bya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ビュ", "byu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ビョ", "byo");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ピャ", "pya");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ピュ", "pyu");
        STANDARD_KANA_ROMANIZATION_LOOKUP.put("ピョ", "pyo");
    }
    //}}}

    //{{{ Define all special override kana romanizations
    protected static final List<Map<String, String>> OVERRIDE_KANA_ROMANIZATION_LOOKUPS;
    static {
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS = new ArrayList<Map<String, String>>();

        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.add(0, new HashMap<String, String>());
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(0).put("キュウ",  "kyu");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(0).put("キョウ",  "kyo");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(0).put("キュ",    "kyu");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(0).put("キョ",    "kyo");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(0).put("クウ",    "ku");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(0).put("コウ",    "ko");

        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.add(1, new HashMap<String, String>());
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(1).put("キュウ",  "kyū");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(1).put("キョウ",  "kyō");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(1).put("キュ",    "kyu");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(1).put("キョ",    "kyo");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(1).put("クウ",    "kū");
        OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(1).put("コウ",    "kō");
    }
    //}}}


    //{{{ String romanizeKana(String, boolean)
    /**
      * Translates each kana character in a string to its romanized equivalent.
      * Characters in the input string that are not kana will be included as-is in the output
      * Any kana character (zenkaku hiragana, zenkaku katakana, hankaku katakana) will be targeted
      * Follows Modified Hepburn romanization
      * Does not consider for particle usage of は (wa) and へ (e)
      *
      * @param  string_with_kana  Input string to perform translation on
      * @param  use_macrons       For long vowels, use unicode "ā", "ū", "ē", and "ō" character with macron shown, otherwise use standard ASCII equivalents
      * @return Content of "string_with_kana" with all kana characters converted to their Hepburn forms
      */
    public static String romanizeKana(String string_with_kana, boolean use_macrons)
    {
        // Don't perform conversions on empty string
        if(string_with_kana.equals("")) {
            return "";
        }

        // Normalize input string entirely to zenkaku katakana
        String normalized_input = normalizeKanaInputs(string_with_kana);
        StringBuffer romanized_string = new StringBuffer();

        // Initialize values used for conversion loop
        int end_slice_pos = normalized_input.length();
        int begin_slice_pos = end_slice_pos;
        Map<String, String> special_kana_lookup = OVERRIDE_KANA_ROMANIZATION_LOOKUPS.get(0);

        while(end_slice_pos > 0) {

            String input_slice = "";
            boolean was_found = false;
            for(int i = MAX_SLICE_SIZE; i > 0; i--) {

                if (i > end_slice_pos) {
                    i = end_slice_pos;
                }
                System.out.println();
                System.out.println("i: " + i);
                begin_slice_pos = end_slice_pos - i;
                System.out.println("a: " + begin_slice_pos);
                System.out.println("b: " + end_slice_pos);

                input_slice = normalized_input.substring(begin_slice_pos, end_slice_pos);
                System.out.println("slice: " + input_slice);
                if(special_kana_lookup.containsKey(input_slice)) {
                    romanized_string.insert(0, special_kana_lookup.get(input_slice));
                    end_slice_pos = begin_slice_pos;
                    was_found = true;
                    break;
                }
                else if(STANDARD_KANA_ROMANIZATION_LOOKUP.containsKey(input_slice)) {
                    romanized_string.insert(0, STANDARD_KANA_ROMANIZATION_LOOKUP.get(input_slice));
                    end_slice_pos = begin_slice_pos;
                    was_found = true;
                    break;
                }
            }

            if(was_found == false) {
                romanized_string.insert(0, input_slice);
                end_slice_pos--;
            }
        }

        return romanized_string.toString();
    }
    //}}}
    //{{{ String romanizeKana(String)
    public static String romanizeKana(String string_with_kana)
    {
        return romanizeKana(string_with_kana, false);
    }
    //}}}


    //{{{ String normalizeKanaInputs(String)
    /**
      * Normalize all kana characters in a given string to zenkaku katakana.
      */
    protected static String normalizeKanaInputs(String raw_kana_input)
    {
        int normalize_kana = KanaConverter.OP_HAN_KATA_TO_ZEN_KATA | KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA;
        return KanaConverter.convertKana(raw_kana_input, normalize_kana);
    }
    //}}}
}
