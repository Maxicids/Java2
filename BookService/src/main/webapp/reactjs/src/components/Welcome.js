import React from "react";
import { Card } from "react-bootstrap";

const Welcome = () => {
  return (
    <Card bg="dark" text="light">
      <Card.Header>Welcome</Card.Header>
      <Card.Body >
          <p>Please sign in</p>
      </Card.Body>
    </Card>
  );
};

export default Welcome;
