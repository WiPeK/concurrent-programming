#include <stdio.h>
#include <unistd.h>

int main() {
	if(fork() != 0) {
		printf("My pid: %d\n", getpid());
		printf("Parent pid %d\n", getppid());
	}
	return 0;
}
