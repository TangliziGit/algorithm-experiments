package Experiment1;

import java.math.BigDecimal;

public class GamePassProbability {

	public double calculatePassProbability(int[] p, int num) {
		if (num == 0 || p == null) return 1.0;

		double[][] dp = new double[num+5][num+5];

		dp[0][0] = 1;
		for (int j=1; j<=num; j++){
			dp[0][j] = dp[0][j-1]*(1-p[j-1]/100.0);
			for (int i=1; i<=j; i++)
				dp[i][j] = dp[i][j-1]*(1-p[j-1]/100.0) + dp[i-1][j-1]*p[j-1]/100.0;
		}

		double ans = 0.0;
		for (int i = (int) Math.ceil(0.7*num); i <= num; i++)
			ans += dp[i][num];

		BigDecimal scaled = new BigDecimal(ans).setScale(5, BigDecimal.ROUND_HALF_UP);
		return Double.parseDouble(scaled.toString());
	}
}
