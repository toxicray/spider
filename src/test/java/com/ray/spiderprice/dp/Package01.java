package com.ray.spiderprice.dp;

/**
 * Package:com.ray.spiderprice.dp
 * *Author:ray
 * *version:...
 * *Created in 2020/1/31  19:04
 **/
public class Package01 {


	public static void main(String[] args) throws InterruptedException {

		System.out.println(Integer.highestOneBit(5));
	}


	int w[], v[], W;

	public int zeroOnePack1(int V, int[] C, int[] W) {
		// 防止无效输入
		if ((V <= 0) || (C.length != W.length)) {
			return 0;
		}

		int n = C.length;

		// dp[i][j]: 对于下标为 0～i 的物品，背包容量为 j 时的最大价值
		int[][] dp = new int[n + 1][V + 1];

		// 背包空的情况下，价值为 0
		dp[0][0] = 0;

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= V; ++j) {
				// 不选物品 i 的话，当前价值就是取到前一个物品的最大价值，也就是 dp[i - 1][j]
				dp[i][j] = dp[i - 1][j];

				// 如果选择物品 i 使得当前价值相对不选更大，那就选取 i，更新当前最大价值
				if ((j >= C[i - 1]) && (dp[i][j] < dp[i - 1][j - C[i - 1]] + W[i - 1])) {
					dp[i][j] = dp[i - 1][j - C[i - 1]] + W[i - 1];
				}
			}
		}

		// 返回，对于所有物品（0～N），背包容量为 V 时的最大价值
		return dp[n][V];
	}


	/**
	 * 01背包问题
	 *
	 * @param V 背包的总容量
	 * @param c 物件的容量数量
	 * @param w 物件的价值数量
	 * @return
	 */
	public int zeroOnePack(int V, int[] c, int[] w) {
		if (V <= 0){
			return 0;
		}
		int n = c.length;
		int[] dp=new int[V+1];
		//背包为空的时候,价值必然为零
		dp[0]=0;
		for (int i = 0; i < n; i++) {
			for (int j= V;j>= c[i];j--){
				dp[j]=Math.max(dp[j],dp[j-c[i]]+w[i]);
			}
		}
		return dp[V];
	}
	public int[] singleNumber(int[] nums) {
		int [] result = new int[2];
		int temp = 0;
		for (int num : nums) {
			temp= temp^num;
		}
		int i = Integer.highestOneBit(temp);
		int num1 = 0;
		int num2 = 0;
		for (int num : nums) {
			if((num&i) == i){
				num1 = num1^num;
			}else{
				num2 = num2^num;
			}
		}
		result[0] = num1;
		result[1] = num2;
		return result;
	}
}
