import sys
N = int(sys.stdin.readline())
arr = [int(sys.stdin.readline()) for _ in range(N)]
sys.stdout.write('\n'.join(map(str, sorted(arr))))