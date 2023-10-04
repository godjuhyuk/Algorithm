N, M = map(int, input().split())
num_arr = list(map(int, input().split()))
ans = 0
prefix = [0] * (N+1)
memo = {}
for key in range(M):
    memo[key] = 0

for i in range(1, N+1):
    prefix[i] = prefix[i-1] + num_arr[i-1]
    memo[prefix[i] % M]+=1

for key in memo:
    l = memo[key]
    if key == 0:
        ans += l + l*(l-1)/2
    elif l!=0:
        ans += l*(l-1)/2
print(int(ans))