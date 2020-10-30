
public static int[] zFunction(String s) {
        int[] z = new int[s.length()];
        for (int i = 1, l = 0, r = 0; i < z.length; ++i) {
            if (i <= r)
                z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < z.length && s.charAt(z[i]) == s.charAt(i + z[i]))
                ++z[i];
            if (r < i + z[i] - 1) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
