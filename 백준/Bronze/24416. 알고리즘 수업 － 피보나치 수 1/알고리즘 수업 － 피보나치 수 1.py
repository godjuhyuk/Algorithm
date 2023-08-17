def fib_dp(n):
    cnt= 0
    fn = [1, 1] + [0] * (n-2)
    for i in range(2, n):
        fn[i] = fn[i-1] + fn[i-2]
        cnt +=1 
    return fn[-1], cnt

print(*fib_dp(int(input())))