this.setupAxiosInterceptors(this.setupJWTToken(token))

setupJWTToken(token) {
    return 'Bearer ' + token
}