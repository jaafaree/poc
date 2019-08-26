package com.jaafar.poc.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaafar.poc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class ExtensionController extends AbstractController<User> {

    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    public ExtensionController(ObjectMapper objectMapper) {
//        super(objectMapper);
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(User.class, new PresentPropertyPolymorphicDeserializer<>(User.class));
//        module.addDeserializer(ProposalItemBaseModel.class, new PresentPropertyPolymorphicDeserializer<>(ProposalItemBaseModel.class));
//        module.addDeserializer(ProposalLocationBaseModel.class, new PresentPropertyPolymorphicDeserializer<>(ProposalLocationBaseModel.class));
//        module.addDeserializer(ProposalContractTypeBaseModel.class, new PresentPropertyPolymorphicDeserializer<>(ProposalContractTypeBaseModel.class));
//        module.addDeserializer(ProposalExceptionBaseModel.class, new PresentPropertyPolymorphicDeserializer<>(ProposalExceptionBaseModel.class));
//        module.addDeserializer(ProposalSignatureBaseModel.class, new PresentPropertyPolymorphicDeserializer<>(ProposalSignatureBaseModel.class));
//        module.addDeserializer(ContractBaseModel.class, new PresentPropertyPolymorphicDeserializer<>(ContractBaseModel.class));
//        module.addDeserializer(ContractItemBaseModel.class, new PresentPropertyPolymorphicDeserializer<>(ContractItemBaseModel.class));
//        objectMapper.registerModule(module);
//
//
//    }

    @Override
    protected List<User> deserialize(String content) {
        try {
            return this.objectMapper.readValue(content, new TypeReference<ArrayList<User>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
