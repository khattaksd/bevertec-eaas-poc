package com.serendevity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.vault.support.VaultTransitContext;

@Converter
public class CipherConverter implements AttributeConverter<String, String> {

    private final VaultTransitContext context;
    private final VaultTransitOperations ops;
    private final String keyName;

    @Autowired
    public CipherConverter(@Value("${vault.keyName}") String vaultKeyName,
            @Value("${vault.context}") String valutContext, VaultTemplate vaultTemplate) {
        this.keyName = vaultKeyName;
        this.context = VaultTransitContext.fromContext(valutContext.getBytes());
        this.ops = vaultTemplate.opsForTransit();
    }

    @Override
    public String convertToDatabaseColumn(final String plaintext) {
        return this.ops.encrypt(this.keyName, plaintext.getBytes(), this.context);
    }

    @Override
    public String convertToEntityAttribute(final String ciphertext) {
        return new String(this.ops.decrypt(this.keyName, ciphertext, this.context));
    }
}
