#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
int main()
{
    void *lib_handle;
    int (*fn)(int,int);
    char *error;

    lib_handle = dlopen("/opt/lib/libDyn.so", RTLD_LAZY);
    if (!lib_handle) {
        fprintf(stderr, "%s\n", dlerror());
        return(1);
    }

    fn = dlsym(lib_handle, "add2ints");
    if ((error = dlerror()) != NULL) {
        fprintf(stderr, "%s\n", error);
        return(1);
    }

    printf("add=%d\n",(*fn)(1,2));
    dlclose(lib_handle);
    return(0);

}
