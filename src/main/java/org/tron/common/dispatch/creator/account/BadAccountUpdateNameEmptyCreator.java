package org.tron.common.dispatch.creator.account;

import com.google.protobuf.ByteString;
import java.util.concurrent.atomic.AtomicLong;
import org.tron.common.crypto.ECKey;
import org.tron.common.dispatch.AbstractTransactionCreator;
import org.tron.common.dispatch.BadCaseTransactionCreator;
import org.tron.common.dispatch.TransactionFactory;
import org.tron.common.dispatch.creator.CreatorCounter;
import org.tron.common.dispatch.creator.TransactionUtils;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.Time;
import org.tron.protos.Contract;
import org.tron.protos.Protocol;
import org.tron.protos.Protocol.Transaction.Contract.ContractType;

public class BadAccountUpdateNameEmptyCreator extends AbstractTransactionCreator implements BadCaseTransactionCreator {
  private AtomicLong serialNum = new AtomicLong(0);

  @Override
  protected Protocol.Transaction create() {
    TransactionFactory.context.getBean(CreatorCounter.class).put(this.getClass().getName());
    Contract.AccountUpdateContract contract = Contract.AccountUpdateContract.newBuilder()
        .setAccountName(ByteString.EMPTY)
        .setOwnerAddress(ownerAddress)
        .build();

    Protocol.Transaction transaction = TransactionUtils.createTransaction(contract, ContractType.AccountUpdateContract);
    transaction = client.signTransaction(transaction, ECKey.fromPrivate(ByteArray.fromHexString(privateKey)));
    return transaction;
  }
}
