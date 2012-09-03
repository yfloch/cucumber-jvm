package cucumber.runtime;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ShellwordsTest {
    @Test
    public void splits_normal_words() {
        String[] split = Shellwords.split("foo bar baz");
        String[] expected = {"foo", "bar", "baz"};
        assertArrayEquals(expected, split);
    }

    @Test
    public void splits_single_quoted_phrases() {
        String[] split = Shellwords.split("foo 'bar baz'");
        String[] expected = {"foo", "bar baz"};
        assertArrayEquals(expected, split);
    }

    @Test
    public void splits_double_quoted_phrases() {
        String[] split = Shellwords.split("\"foo bar\" baz");
        String[] expected = {"foo bar", "baz"};
        assertArrayEquals(expected, split);
    }

    @Test
    public void respects_escaped_characters() {
        String[] split = Shellwords.split("foo\\ bar baz");
        String[] expected = {"foo bar", "baz"};
        assertArrayEquals(expected, split);
    }
}
