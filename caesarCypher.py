for symbol in message:
    if symbol in SYMBOLS:
        #find index of the current symbol
        symbolIndex = SYMBOLS.find(symbol)
        
        if mode == 'encrypt':
            #index + 13
            translatedIndex = symbolIndex + key
        elif mode == 'decrypt':
            translatedIndex = symbolIndex - key
        #if index is surpasing the length of the SYMBOLS (65) then we substract with 65
        if translatedIndex >= len(SYMBOLS):
            print('translatedIndex >= len(SYMBOLS) ' + str(translatedIndex))
            translatedIndex = translatedIndex - len(SYMBOLS)
            #for decrypt only
        elif translatedIndex < 0:
            translatedIndex = translatedIndex + len(SYMBOLS)
            
        translated = translated + SYMBOLS[translatedIndex]
        print('translated = translated + SYMBOLS[translatedIndex] ' + str(SYMBOLS[translatedIndex]))
    else:
        translated = translated + symbol
        
print(translated)
