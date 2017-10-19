#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {
	int i =argc-1;
	for(i; i >= 0; i--) {
		printf("%s \n", argv[i]);
	}
	return 0;
}
