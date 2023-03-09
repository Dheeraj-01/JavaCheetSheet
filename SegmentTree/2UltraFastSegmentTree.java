    // Ultra Fast Segment Tree inspired from Tlatoani's code
    // https://codeforces.com/contest/1208/submission/59470935
    class ST {
        final long[] val;
        final int treeFrom;
        final int length;
        final long neutralElem;
 
        long func (long a, long b) {
            return max(a, b);
        }
 
        ST(int treeFrom, int treeTo, long neutralElem) {
            this.neutralElem = neutralElem;
            this.treeFrom = treeFrom;
            int length = treeTo - treeFrom + 1;
            int l = Integer.highestOneBit(length);
            if(length > l) l <<= 1;
            val = new long[l << 1];
            this.length = l;
            // Important to fill the tree with neutral values
            Arrays.fill(val, neutralElem);
        }
 
        ST(long[] arr, long neutralElem) {
            this.neutralElem = neutralElem;
            int length = arr.length;
            this.treeFrom = 0;
            int l = Integer.highestOneBit(length);
            if(length > l) l <<= 1;
            val = new long[l << 1];
            this.length = l;
            // Important to fill the tree with neutral values
            Arrays.fill(val, neutralElem);
 
            for(int i = 0; i < length; i++) {
                set(i, arr[i]);
            }
        }
 
        void increase(int index, long delta) {
            int node = index - treeFrom + length; // gives the index of leaf
            val[node] += delta;
            // going upwards from leaf
            for (node >>= 1; node > 0; node >>= 1) {
                val[node] = func(val[node << 1], val[(node << 1) + 1]);
            }
        }
 
        void set(int index, long value) {
            int node = index - treeFrom + length; // gives the index of the leaf
            val[node] = value;
            // going upwards from leaf
            for (node >>= 1; node > 0; node >>= 1) {
                val[node] = func(val[node << 1], val[(node << 1) + 1]);
            }
        }
 
        long query(int from, int to) {
            from += length - treeFrom; // index of left leaf
            to += length - treeFrom + 1; // index of right leaf
 
            long res = neutralElem;
 
            for (; from + (from & -from) <= to; from += from & -from) {
                res = func(res, val[from / (from & -from)]);
            }
 
            for (; to - (to & -to) >= from; to -= to & -to) {
                res = func(val[(to - (to & -to)) / (to & -to)], res);
            }
 
            return res;
        }
    }
