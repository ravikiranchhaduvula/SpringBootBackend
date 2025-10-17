package Arrays;

import java.util.Arrays;

public class TwoPointer {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 2, 2, 3, 3, 4};
        int writerindex= removeDuplicates(nums);
        System.out.println(writerindex);
    }
        public static int removeDuplicates(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;
            int w = 1; // next position to write a new unique value
            for (int i = 1; i < n; i++) {
                if (nums[i] != nums[w - 1]) {
                    System.out.println("Match "+"i: "+i + " nums[i]: "+nums[i]+" w: "+w+" nums[w-1]: "+nums[w-1]);
                    System.out.println("Match "+Arrays.toString(nums));
                    nums[w] = nums[i];
                    w++;
                } else {
                    System.out.println("MisMatch "+"i: "+i + " nums[i]: "+nums[i]+" w: "+w+" nums[w-1]: "+nums[w-1]);
                    System.out.println("MisMatch "+Arrays.toString(nums));
                }
            }
            System.out.println("Final "+ Arrays.toString(nums));
            return w;
        }
}
